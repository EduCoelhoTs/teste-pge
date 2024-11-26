package br.com.testpge.restaurantmanager.shared.exceptions;

import java.util.HashSet;
import java.util.Set;

public class ErrorsContainer {

    private final Set<RuntimeException> errors = new HashSet<>();

    public Set<RuntimeException> getErrors() {
        return this.errors;
    }

    public void addErrors(Set<? extends RuntimeException> errorsList) {
        if (errorsList == null || errorsList.isEmpty()) {
            return;
        }

        for (RuntimeException error : errorsList) {
            if (error instanceof RuntimeException) {
                this.errors.add(error);
            }
        }
    }

    public void addError(RuntimeException error) {
        if (error instanceof RuntimeException) {
            errors.add(error);
        }
    }
}
