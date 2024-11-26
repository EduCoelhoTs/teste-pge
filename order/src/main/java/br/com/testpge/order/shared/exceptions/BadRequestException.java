package br.com.testpge.order.shared.exceptions;

import java.util.Set;

public class BadRequestException extends CustomException {

    public BadRequestException() {
        super("bad request");
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Set<String> errors) {
        super(message, errors);
    }
}
