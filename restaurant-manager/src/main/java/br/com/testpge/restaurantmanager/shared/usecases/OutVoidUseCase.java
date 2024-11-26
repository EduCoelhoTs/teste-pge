package br.com.testpge.restaurantmanager.shared.usecases;

public interface OutVoidUseCase<Input> {

    void execute(Input input) throws Exception;
}
