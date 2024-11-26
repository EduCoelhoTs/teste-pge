package br.com.testpge.restaurantmanager.shared.valueobjects;

import br.com.testpge.restaurantmanager.shared.exceptions.BadRequestException;
import br.com.testpge.restaurantmanager.shared.exceptions.ErrorsContainer;
import br.com.testpge.restaurantmanager.shared.validations.ValidatorFn;

public abstract class ValueObject<T> {

    protected T value;
    private final ValidatorFn<T> validator;
    private final String errorMessage;
    private final ErrorsContainer errorsContainer;

    public ValueObject(
            ValidatorFn<T> validator,
            String errorMessage,
            ErrorsContainer errorsContainer
    ) {
        this.validator = validator;
        this.errorMessage = errorMessage;
        this.errorsContainer = errorsContainer;
    }

    public ValueObject(
            ValidatorFn<T> validator,
            String errorMessage
    ) {
        this.validator = validator;
        this.errorMessage = errorMessage;
        this.errorsContainer = null;
    }

    public T getValue() {
        return this.value;
    }

    protected void validate() throws RuntimeException {
        final var validValue = this.validator.validate(this.value);
        if (validValue) {
            return;
        }

        if (this.errorsContainer == null) {
            throw new BadRequestException(this.errorMessage);
        }

        this.errorsContainer.addError(
                new BadRequestException(this.errorMessage)
        );
    }

    protected void setValue(T value) {
        this.value = value;
        this.validate();
    }
}
