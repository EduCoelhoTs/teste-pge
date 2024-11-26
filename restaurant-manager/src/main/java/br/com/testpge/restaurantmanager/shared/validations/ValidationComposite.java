package br.com.testpge.restaurantmanager.shared.validations;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import br.com.testpge.restaurantmanager.shared.exceptions.BadRequestException;
import br.com.testpge.restaurantmanager.shared.exceptions.ErrorCompositionException;
import br.com.testpge.restaurantmanager.shared.exceptions.ErrorsContainer;
import br.com.testpge.restaurantmanager.shared.exceptions.InternalServerErrorException;

public class ValidationComposite<T> implements Validation<T> {

    private final Set<String> compositeErrorsMessages = new HashSet<>();
    private final Set<Validation<T>> validations;
    private final ErrorsContainer errorsContainer;

    public ValidationComposite(
            final Set<Validation<T>> validations,
            final ErrorsContainer errorsContainer
    ) {
        this.validations = Collections.unmodifiableSet(validations);
        this.errorsContainer = errorsContainer;
    }

    public ValidationComposite(final Set<Validation<T>> validations) {
        this.validations = Collections.unmodifiableSet(validations);
        this.errorsContainer = null;
    }

    @Override
    public void validate(T input) throws Exception {
        this.addErrorsContainerInCompositeErrors();

        for (Validation<T> validation : this.validations) {
            try {
                validation.validate(input);
            } catch (Exception error) {
                if (error instanceof ErrorCompositionException errorComposition) {
                    this.compositeErrorsMessages.addAll(errorComposition.getErrors());
                } else if (error instanceof RuntimeException runtimeException) {

                    if (error instanceof InternalServerErrorException) {
                        throw error;
                    }

                    this.compositeErrorsMessages.add(runtimeException.getMessage());
                } else {
                    throw new IllegalStateException("Unhandled exception type: ", error);
                }
            }
        }

        if (this.compositeErrorsMessages.isEmpty()) {
            return;
        }

        if (this.compositeErrorsMessages.size() > 1) {
            throw new ErrorCompositionException(
                    "Bad Request",
                    this.compositeErrorsMessages
            );
        }

        throw new BadRequestException(this.compositeErrorsMessages.iterator().next());
    }

    private void addErrorsContainerInCompositeErrors() {
        if (errorsContainer != null) {

            for (RuntimeException error : errorsContainer.getErrors()) {
                if (error instanceof ErrorCompositionException errorComposition) {
                    this.compositeErrorsMessages.addAll(errorComposition.getErrors());
                } else if (error instanceof RuntimeException runtimeException) {

                    if (error instanceof InternalServerErrorException) {
                        throw error;
                    }

                    this.compositeErrorsMessages.add(runtimeException.getMessage());
                } else {
                    throw new IllegalStateException("Unhandled exception type: ", error);
                }
            }
        }
    }
}
