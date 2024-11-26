package br.com.testpge.order.usecases;

import java.util.List;
import java.util.Objects;

import br.com.testpge.order.entities.OrderEntity;
import br.com.testpge.order.repositories.OrderRepository;
import br.com.testpge.order.usecases.dtos.OrderOutput;

public class FinAllOrderImpl implements FinAllOrderUsecase {

    private final OrderRepository repository;

    public FinAllOrderImpl(OrderRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public List<OrderOutput> execute() throws Exception {

        final List<OrderEntity> result = this.repository.findAll();

        return result
                .stream()
                .map(OrderOutput::fromEntity)
                .toList();
    }
}
