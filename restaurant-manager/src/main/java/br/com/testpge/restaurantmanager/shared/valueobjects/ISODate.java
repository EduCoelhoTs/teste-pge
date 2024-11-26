package br.com.testpge.restaurantmanager.shared.valueobjects;

import br.com.testpge.restaurantmanager.shared.exceptions.ErrorsContainer;
import br.com.testpge.restaurantmanager.shared.validations.Validator;
import br.com.testpge.restaurantmanager.shared.validations.ValidatorFn;

public class ISODate extends ValueObject<String> {

    public ISODate(String value, String errorMessage, ErrorsContainer errorsContainer) {
        super(createValidator(), errorMessage, errorsContainer);
        setValue(value);
    }

    public ISODate(String value, String errorMessage) {
        super(createValidator(), errorMessage);
        setValue(value);
    }

    public ISODate(String value, ErrorsContainer errorsContainer) {
        super(createValidator(), "date in invalid format", errorsContainer);
        setValue(value);
    }

    public ISODate(String value) {
        super(createValidator(), "date in invalid format");
        setValue(value);
    }

    private static ValidatorFn<String> createValidator() {
        return (date) -> Validator.isISODate(date);
    }
}
