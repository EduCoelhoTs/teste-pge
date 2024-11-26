package br.com.testpge.restaurantmanager.shared.validations;

import br.com.testpge.restaurantmanager.shared.exceptions.ErrorsContainer;

public interface DataValidator<T> {

    void validate(T entity, ErrorsContainer errorsContainer) throws Exception;

    void validate(T entity) throws Exception;
}
