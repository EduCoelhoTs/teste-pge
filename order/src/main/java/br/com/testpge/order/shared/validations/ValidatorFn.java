package br.com.testpge.order.shared.validations;

@FunctionalInterface
public interface ValidatorFn<T> {

    Boolean validate(T value);
}
