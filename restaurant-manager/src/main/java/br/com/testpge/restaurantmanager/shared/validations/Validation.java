package br.com.testpge.restaurantmanager.shared.validations;

public interface Validation<T> {

    void validate(T input) throws Exception;
}
