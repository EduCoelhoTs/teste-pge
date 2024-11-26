package br.com.testpge.restaurantmanager.shared.validations;

@FunctionalInterface
public interface ValidatorFn<T> {

    Boolean validate(T value);
}
