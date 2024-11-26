package br.com.testpge.restaurantmanager.shared.exceptions;

import java.util.Set;

public class ErrorCompositionException extends CustomException {

    public ErrorCompositionException(Set<String> errors) {
        super("Bad Request", errors);
    }

    public ErrorCompositionException(String message, Set<String> errors) {
        super(message, errors);
    }
}
