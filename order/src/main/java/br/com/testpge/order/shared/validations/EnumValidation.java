package br.com.testpge.order.shared.validations;

import java.util.Arrays;

import br.com.testpge.order.shared.constants.ErrorMessages;
import br.com.testpge.order.shared.exceptions.BadRequestException;
import br.com.testpge.order.shared.exceptions.InternalServerErrorException;

public class EnumValidation<T, E extends Enum<?>> implements Validation<T> {

    private final String fieldName;
    private final Class<E> enumToCompare;
    private final boolean isRequired;
    private final String message;
    private final Exception error;
    private final Runnable log;

    public EnumValidation(
            String fieldName,
            Class<E> enumToCompare,
            Boolean isRequired,
            String message,
            Exception error,
            Runnable log
    ) {
        this.fieldName = fieldName;
        this.enumToCompare = enumToCompare;
        this.isRequired = isRequired;
        this.message = message;
        this.error = error;
        this.log = log;
    }

    public EnumValidation(
            String fieldName,
            Class<E> enumToCompare,
            Boolean isRequired
    ) {
        this.fieldName = fieldName;
        this.enumToCompare = enumToCompare;
        this.isRequired = isRequired;
        this.message = null;
        this.error = null;
        this.log = null;
    }

    public EnumValidation(String fieldName, Class<E> enumToCompare) {
        this.fieldName = fieldName;
        this.enumToCompare = enumToCompare;
        this.isRequired = false;
        this.message = null;
        this.error = null;
        this.log = null;
    }

    public EnumValidation(
            String fieldName,
            Class<E> enumToCompare,
            Boolean isRequired,
            String message
    ) {
        this.fieldName = fieldName;
        this.enumToCompare = enumToCompare;
        this.isRequired = isRequired;
        this.message = message;
        this.error = null;
        this.log = null;
    }

    public EnumValidation(
            String fieldName,
            Class<E> enumToCompare,
            Boolean isRequired,
            Exception error
    ) {
        this.fieldName = fieldName;
        this.enumToCompare = enumToCompare;
        this.isRequired = isRequired;
        this.message = null;
        this.error = error;
        this.log = null;
    }

    @Override
    public void validate(T input) throws Exception {
        if (this.fieldName == null || this.fieldName.isEmpty()) {
            throw new InternalServerErrorException(
                    "EmailValidation: fieldName can not be empty"
            );
        }
        if (this.enumToCompare == null) {
            throw new InternalServerErrorException(
                    "EmailValidation: enumToCompare can not be empty"
            );
        }

        final Object fieldValue = this.getFieldValue(input, this.fieldName);
        if (this.isRequired) {
            isRequiredValidate(input);
        } else if (fieldValue == null) {
            return;
        }

        E[] enumValues = this.enumToCompare.getEnumConstants();
        boolean isValid = Arrays.stream(enumValues)
                .anyMatch(enumConstant -> enumConstant.name().equals(fieldValue.toString()));

        if (!isValid) {
            if (this.log != null) {
                this.log.run();
            }

            if (this.error != null) {
                throw this.error;
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
