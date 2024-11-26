package br.com.testpge.order.usecases;

import br.com.testpge.order.shared.data.dtos.EntityIdOutput;
import br.com.testpge.order.shared.usecases.UseCase;
import br.com.testpge.order.usecases.dtos.CreateOrderCommand;

public interface CreateOrderUsecase
        extends UseCase<CreateOrderCommand, EntityIdOutput> {

}
