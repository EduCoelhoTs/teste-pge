package br.com.testpge.order.shared.usecases;

public interface UseCase<Input, Output> {

    Output execute(Input input) throws Exception;
}
