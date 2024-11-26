package br.com.testpge.order.usecases;

import br.com.testpge.order.shared.usecases.UseCase;
import br.com.testpge.order.usecases.dtos.FindOrderByIdInput;
import br.com.testpge.order.usecases.dtos.OrderOutput;

public interface FindOrderByIdUsecase
        extends UseCase<FindOrderByIdInput, OrderOutput> {

}
