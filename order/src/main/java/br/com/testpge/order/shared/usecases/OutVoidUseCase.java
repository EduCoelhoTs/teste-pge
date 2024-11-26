package br.com.testpge.order.shared.usecases;

public interface OutVoidUseCase<Input> {

    void execute(Input input) throws Exception;
}
