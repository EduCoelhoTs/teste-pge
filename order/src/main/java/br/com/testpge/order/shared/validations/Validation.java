package br.com.testpge.order.shared.validations;

public interface Validation<T> {

    void validate(T input) throws Exception;
}
