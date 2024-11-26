package br.com.testpge.restaurantmanager.consumptiontable.usecases;

import java.util.List;
import java.util.Objects;

import br.com.testpge.restaurantmanager.consumptiontable.entities.ConsumptionTableEntity;
import br.com.testpge.restaurantmanager.consumptiontable.repositories.ConsumptionTableRepository;
import br.com.testpge.restaurantmanager.consumptiontable.usecases.dtos.ConsumptionTableOutput;

public class FinAllConsumptionTableImpl implements FinAllConsumptionTableUsecase {

    private final ConsumptionTableRepository repository;

    public FinAllConsumptionTableImpl(ConsumptionTableRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public List<ConsumptionTableOutput> execute() throws Exception {

        final List<ConsumptionTableEntity> result = this.repository.findAll();

        return result
                .stream()
                .map(item -> ConsumptionTableOutput.fromEntity(item))
                .toList();
    }
}
