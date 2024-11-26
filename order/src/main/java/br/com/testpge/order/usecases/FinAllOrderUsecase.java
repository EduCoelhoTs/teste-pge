package br.com.testpge.order.usecases;

import java.util.List;

import br.com.testpge.order.shared.usecases.OutOnlyUseCase;
import br.com.testpge.order.usecases.dtos.OrderOutput;

public interface FinAllOrderUsecase
        extends OutOnlyUseCase<List<OrderOutput>> {

}
