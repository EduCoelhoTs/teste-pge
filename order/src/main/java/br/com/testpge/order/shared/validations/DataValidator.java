package br.com.testpge.order.shared.validations;

import br.com.testpge.order.shared.exceptions.ErrorsContainer;

public interface DataValidator<T> {

    void validate(T entity, ErrorsContainer errorsContainer) throws Exception;

    void validate(T entity) throws Exception;
}
