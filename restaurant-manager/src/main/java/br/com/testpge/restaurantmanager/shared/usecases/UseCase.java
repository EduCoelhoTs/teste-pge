package br.com.testpge.restaurantmanager.shared.usecases;

public interface UseCase<Input, Output> {

    Output execute(Input input) throws Exception;
}
