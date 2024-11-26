package br.com.testpge.restaurantmanager.shared.exceptions;

import java.util.Set;

public class NotFoundException extends CustomException {

    public NotFoundException() {
        super("resource not found");
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Set<String> errors) {
        super(message, errors);
    }
}
