package br.com.testpge.restaurantmanager.shared.exceptionshandlers;

import java.util.Set;

public class RestErrorResponse {

    final public int status;
    final public String message;
    final public Set<String> errors;

    public RestErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.errors = null;
    }

    public RestErrorResponse(int status, String message, Set<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
}
