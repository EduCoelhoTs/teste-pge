package br.com.testpge.order.shared.usecases;

public interface OutOnlyUseCase<Output> {

    Output execute() throws Exception;
}
