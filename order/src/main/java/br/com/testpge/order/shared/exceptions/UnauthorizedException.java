package br.com.testpge.order.shared.exceptions;

import java.util.Set;

public class UnauthorizedException extends CustomException {

    public UnauthorizedException() {
        super("unauthorazed");
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Set<String> errors) {
        super(message, errors);
    }
}
