package br.com.testpge.restaurantmanager.shared.usecases;

public interface OutOnlyUseCase<Output> {

    Output execute() throws Exception;
}
