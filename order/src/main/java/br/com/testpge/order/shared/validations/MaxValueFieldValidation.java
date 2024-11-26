package br.com.testpge.order.shared.validations;

import br.com.testpge.order.shared.constants.ErrorMessages;
import br.com.testpge.order.shared.exceptions.BadRequestException;
import br.com.testpge.order.shared.exceptions.InternalServerErrorException;

public class MaxValueFieldValidation<T> implements Validation<T> {

    private final String fieldName;
    private final Double maxValue;
    private final boolean isRequired;
    private final String message;
    private final Exception error;
    private final Runnable log;

    public MaxValueFieldValidation(
            String fieldName,
            Double maxValue,
            Boolean isRequired,
            String message,
            Exception error,
            Runnable log
    ) {
        this.fieldName = fieldName;
        this.maxValue = maxValue;
        this.isRequired = isRequired;
        this.message = message;
        this.error = error;
        this.log = log;
    }

    public MaxValueFieldValidation(
            String fieldName,
            Double maxValue,
            Boolean isRequired
    ) {
        this.fieldName = fieldName;
        this.maxValue = maxValue;
        this.isRequired = isRequired;
        this.message = null;
        this.error = null;
        this.log = null;
    }

    public MaxValueFieldValidation(String fieldName, Double maxValue) {
        this.fieldName = fieldName;
        this.maxValue = maxValue;
        this.isRequired = false;
        this.message = null;
        this.error = null;
        this.log = null;
    }

    public MaxValueFieldValidation(
            String fieldName,
            Double maxValue,
            Boolean isRequired,
            String message
    ) {
        this.fieldName = fieldName;
        this.maxValue = maxValue;
        this.isRequired = isRequired;
        this.message = message;
        this.error = null;
        this.log = null;
    }

    public MaxValueFieldValidation(
            String fieldName,
            Double maxValue,
            Boolean isRequired,
            Exception error
    ) {
        this.fieldName = fieldName;
        this.maxValue = maxValue;
        this.isRequired = isRequired;
        this.message = null;
        this.error = error;
        this.log = null;
    }

    @Override
    public void validate(T input) throws Exception {
        if (fieldName == null || fieldName.isEmpty()) {
            throw new InternalServerErrorException(
                    "MaxValueFieldValidation: fieldName can not be empty"
            );
        }

        if (this.isRequired) {
            this.isRequiredValidate(input);
        } else if (this.getFieldValue(input, this.fieldName) == null) {
            return;
        }

        Object field = this.getFieldValue(input, this.fieldName);
        if (!(field instanceof Number)) {
            throw new BadRequestException(ErrorMessages.invalidFormat(this.fieldName));
        }

        Number fieldValue = (Number) field;

        if (fieldValue.doubleValue() > this.maxValue) {
            if (log != null) {
                log.run();
            }

            if (error != null) {
                throw error;
            }

            throw new BadRequestException(
                    this.message != null
                            ? this.message
                            : ErrorMessages.maxValue(this.fieldName, this.maxValue.intValue())
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
