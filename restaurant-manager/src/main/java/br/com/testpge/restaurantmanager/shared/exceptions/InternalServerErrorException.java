package br.com.testpge.restaurantmanager.shared.exceptions;

import java.util.Set;

public class InternalServerErrorException extends CustomException {

    public InternalServerErrorException() {
        super("internal server error");
    }

    public InternalServerErrorException(String message) {
        super(message);
    }

    public InternalServerErrorException(String message, Set<String> errors) {
        super(message, errors);
    }
}
