package br.com.testpge.restaurantmanager.shared.exceptions;

import java.util.Set;

public abstract class CustomException extends RuntimeException {

    final public String message;
    final public Set<String> errors;

    public CustomException(String message) {
        super(message);
        this.message = message;
        this.errors = null;
    }

    public CustomException(String message, Set<String> errors) {
        super(message);
        this.message = message;
        this.errors = errors;
    }

    public Set<String> getErrors() {
        return this.errors;
    }
}
