package br.com.testpge.order.shared.validations;

import br.com.testpge.order.shared.constants.ErrorMessages;
import br.com.testpge.order.shared.exceptions.BadRequestException;
import br.com.testpge.order.shared.exceptions.InternalServerErrorException;

public class BooleanValidation<T> implements Validation<T> {

    private final String fieldName;
    private final Boolean isRequired;
    private final String message;
    private final Exception error;
    private final Runnable log;

    public BooleanValidation(
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

    public BooleanValidation(String fieldName, Boolean isRequired) {
        this(fieldName, isRequired, null, null, null);
    }

    public BooleanValidation(String fieldName) {
        this(fieldName, false, null, null, null);
    }

    public BooleanValidation(
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

    public BooleanValidation(
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

    @Override
    public void validate(T input) throws Exception {
        if (this.fieldName == null || this.fieldName.isEmpty()) {
            throw new InternalServerErrorException(
                    "BooleanValidation: fieldName can not be empty"
            );
        }

        Object fieldValue = this.getFieldValue(input, this.fieldName);

        if (this.isRequired) {
            this.isRequiredValidate(input);
        } else if (this.getFieldValue(input, this.fieldName) == null) {
            return;
        }

        if (!(fieldValue instanceof Boolean)) {
            if (log != null) {
                log.run();
            }

            if (error != null) {
                throw error;
            }

            throw new BadRequestException(
                    message != null
                            ? message
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
