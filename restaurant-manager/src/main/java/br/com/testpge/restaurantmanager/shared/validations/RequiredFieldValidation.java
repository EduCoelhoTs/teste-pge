package br.com.testpge.restaurantmanager.shared.validations;

import br.com.testpge.restaurantmanager.shared.constants.ErrorMessages;
import br.com.testpge.restaurantmanager.shared.exceptions.BadRequestException;
import br.com.testpge.restaurantmanager.shared.exceptions.InternalServerErrorException;

public class RequiredFieldValidation<T> implements Validation<T> {

    private final String fieldName;
    private final String message;
    private final Exception error;
    private final Runnable log;

    public RequiredFieldValidation(
            String fieldName,
            String message,
            Exception error,
            Runnable log
    ) {
        this.fieldName = fieldName;
        this.message = message;
        this.error = error;
        this.log = log;
    }

    public RequiredFieldValidation(
            String fieldName,
            Exception error
    ) {
        this.fieldName = fieldName;
        this.message = null;
        this.error = error;
        this.log = null;
    }

    public RequiredFieldValidation(
            String fieldName,
            String message
    ) {
        this.fieldName = fieldName;
        this.message = message;
        this.error = null;
        this.log = null;
    }

    public RequiredFieldValidation(String fieldName) {
        this.fieldName = fieldName;
        this.message = null;
        this.error = null;
        this.log = null;
    }

    @Override
    public void validate(T input) throws Exception {
        if (fieldName == null || fieldName.isEmpty()) {
            throw new InternalServerErrorException(
                    "EmailValidation: fieldName can not be empty"
            );
        }

        Object fieldValue = this.getFieldValue(input, this.fieldName);
        if (fieldValue == null || this.fieldName.isEmpty()) {
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

    private Object getFieldValue(T input, String fieldName) throws Exception {
        var field = input.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);

        return field.get(input);
    }
}
