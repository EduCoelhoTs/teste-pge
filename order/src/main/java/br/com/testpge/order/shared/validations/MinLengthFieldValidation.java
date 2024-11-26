package br.com.testpge.order.shared.validations;

import br.com.testpge.order.shared.constants.ErrorMessages;
import br.com.testpge.order.shared.exceptions.BadRequestException;
import br.com.testpge.order.shared.exceptions.InternalServerErrorException;

public class MinLengthFieldValidation<T> implements Validation<T> {

    private final String fieldName;
    private final int minLength;
    private final boolean isRequired;
    private final String message;
    private final Exception error;
    private final Runnable log;

    public MinLengthFieldValidation(
            String fieldName,
            int minLength,
            Boolean isRequired,
            String message,
            Exception error,
            Runnable log
    ) {
        this.fieldName = fieldName;
        this.minLength = minLength;
        this.isRequired = isRequired;
        this.message = message;
        this.error = error;
        this.log = log;
    }

    public MinLengthFieldValidation(
            String fieldName,
            int minLength,
            Boolean isRequired
    ) {
        this.fieldName = fieldName;
        this.minLength = minLength;
        this.isRequired = isRequired;
        this.message = null;
        this.error = null;
        this.log = null;
    }

    public MinLengthFieldValidation(String fieldName, int minLength) {
        this.fieldName = fieldName;
        this.minLength = minLength;
        this.isRequired = false;
        this.message = null;
        this.error = null;
        this.log = null;
    }

    public MinLengthFieldValidation(
            String fieldName,
            int minLength,
            Boolean isRequired,
            String message
    ) {
        this.fieldName = fieldName;
        this.minLength = minLength;
        this.isRequired = isRequired;
        this.message = message;
        this.error = null;
        this.log = null;
    }

    public MinLengthFieldValidation(
            String fieldName,
            int minLength,
            Boolean isRequired,
            Exception error
    ) {
        this.fieldName = fieldName;
        this.minLength = minLength;
        this.isRequired = isRequired;
        this.message = null;
        this.error = error;
        this.log = null;
    }

    @Override
    public void validate(T input) throws Exception {
        if (fieldName == null || fieldName.isEmpty()) {
            throw new InternalServerErrorException(
                    "MinLengthFieldValidation: fieldName can not be empty"
            );
        }

        if (this.isRequired) {
            this.isRequiredValidate(input);
        } else if (this.getFieldValue(input, this.fieldName) == null) {
            return;
        }

        Object field = this.getFieldValue(input, this.fieldName);
        if (!(field instanceof String)) {
            throw new BadRequestException(ErrorMessages.invalidFormat(this.fieldName));
        }

        String fieldValue = field.toString().trim();

        if (fieldValue.length() < this.minLength) {
            if (log != null) {
                log.run();
            }

            if (error != null) {
                throw error;
            }

            throw new BadRequestException(
                    this.message != null
                            ? this.message
                            : ErrorMessages.minLenth(this.fieldName, this.minLength)
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
