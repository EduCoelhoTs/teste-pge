package br.com.testpge.order.shared.valueobjects;

import br.com.testpge.order.shared.exceptions.ErrorsContainer;
import br.com.testpge.order.shared.validations.Validator;
import br.com.testpge.order.shared.validations.ValidatorFn;

public class Identifier extends ValueObject<String> {

    public Identifier(String value, String errorMessage, ErrorsContainer errorsContainer) {
        super(createValidator(), errorMessage, errorsContainer);
        setValue(value);
    }

    public Identifier(String value, String errorMessage) {
        super(createValidator(), errorMessage);
        setValue(value);
    }

    public Identifier(String value, ErrorsContainer errorsContainer) {
        super(createValidator(), "id in invalid format", errorsContainer);
        setValue(value);
    }

    public Identifier(String value) {
        super(createValidator(), "id in invalid format");
        setValue(value);
    }

    private static ValidatorFn<String> createValidator() {
        return (uuid) -> Validator.isUUID(uuid);
    }
}
