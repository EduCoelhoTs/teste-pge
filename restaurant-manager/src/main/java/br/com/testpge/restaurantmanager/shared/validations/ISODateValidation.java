package br.com.testpge.restaurantmanager.shared.validations;

import br.com.testpge.restaurantmanager.shared.constants.ErrorMessages;
import br.com.testpge.restaurantmanager.shared.exceptions.BadRequestException;
import br.com.testpge.restaurantmanager.shared.exceptions.InternalServerErrorException;
import br.com.testpge.restaurantmanager.shared.valueobjects.ISODate;

public class ISODateValidation<T> implements Validation<T> {

    private final String fieldName;
    private final boolean isRequired;
    private final String message;
    private final Exception error;
    private final Runnable log;

    public ISODateValidation(
            String fieldName,
            Boolean isRequired,
            String message,
            Exception error,
            Runnable log
    ) {
        this.fieldName = fieldName;
        this.isRequired = isRequired;
        this.message = message;
        this.error = error;
        this.log = log;
    }

    public ISODateValidation(
            String fieldName,
            Boolean isRequired
    ) {
        this.fieldName = fieldName;
        this.isRequired = isRequired;
        this.message = null;
        this.error = null;
        this.log = null;
    }

    public ISODateValidation(String fieldName) {
        this.fieldName = fieldName;
        this.isRequired = false;
        this.message = null;
        this.error = null;
        this.log = null;
    }

    public ISODateValidation(
            String fieldName,
            Boolean isRequired,
            Exception error
    ) {
        this.fieldName = fieldName;
        this.isRequired = isRequired;
        this.message = null;
        this.error = error;
        this.log = null;
    }

    public ISODateValidation(
            String fieldName,
            Boolean isRequired,
            String message
    ) {
        this.fieldName = fieldName;
        this.isRequired = isRequired;
        this.message = message;
        this.error = null;
        this.log = null;
    }

    @Override
    public void validate(T input) throws Exception {
        if (fieldName == null || fieldName.isEmpty()) {
            throw new InternalServerErrorException(
                    "ISODateValidation: fieldName can not be empty"
            );
        }

        if (this.isRequired) {
            this.isRequiredValidate(input);
        } else if (this.getFieldValue(input, this.fieldName) == null) {
            return;
        }

        String fieldValue;
        Object field = this.getFieldValue(input, this.fieldName);
        if (field instanceof ISODate isoDate) {
            fieldValue = isoDate.getValue();
        } else {
            fieldValue = field != null ? field.toString() : null;
        }

        final boolean isValid = Validator.isISODate(fieldValue);

        if (!isValid) {
            if (log != null) {
                log.run();
            }

            if (error != null) {
                throw error;
            }

            throw new BadRequestException(
                    this.message != null
                            ? this.message
                            : ErrorMessages.invalidFormat(this.fieldName)
            );
        }
    }

    private void isRequiredValidate(T input) throws Exception {
        final var isRequiredValidator = new RequiredFieldValidation<T>(
                this.fieldName
        );
        isRequiredValidator.validate(input);
    }

    private Object getFieldValue(T input, String fieldName) throws Exception {
        var field = input.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);

        return field.get(input);
    }
}
