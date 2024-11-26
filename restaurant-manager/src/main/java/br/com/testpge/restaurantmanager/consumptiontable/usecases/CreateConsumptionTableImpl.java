package br.com.testpge.restaurantmanager.consumptiontable.usecases;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import br.com.testpge.restaurantmanager.consumptiontable.entities.ConsumptionTableEntity;
import br.com.testpge.restaurantmanager.consumptiontable.enums.ConsumptionTableStatus;
import br.com.testpge.restaurantmanager.consumptiontable.repositories.ConsumptionTableRepository;
import br.com.testpge.restaurantmanager.consumptiontable.usecases.dtos.CreateConsumptionTableCommand;
import br.com.testpge.restaurantmanager.shared.data.dtos.EntityIdOutput;
import br.com.testpge.restaurantmanager.shared.exceptions.ErrorsContainer;
import br.com.testpge.restaurantmanager.shared.validations.Validation;
import br.com.testpge.restaurantmanager.shared.validations.ValidationComposite;
import br.com.testpge.restaurantmanager.shared.validations.ValidationFieldBuilder;

public class CreateConsumptionTableImpl implements CreateConsumptionTableUsecase {

    private final ConsumptionTableRepository repository;

    public CreateConsumptionTableImpl(ConsumptionTableRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public EntityIdOutput execute(CreateConsumptionTableCommand data) throws Exception {
        this.validateCreateConsumptionTableCommand(data);

        final var entity = new ConsumptionTableEntity();
        entity.setId(UUID.randomUUID());
        entity.setName(data.name());
        entity.setStatus(ConsumptionTableStatus.FREE);
        this.repository.save(entity);

        return new EntityIdOutput(entity.getId().toString());
    }

    private void validateCreateConsumptionTableCommand(
            CreateConsumptionTableCommand data
    ) throws Exception {
        final var errorsContainer = new ErrorsContainer();
        final Set<Validation<CreateConsumptionTableCommand>> validations = new HashSet<>();

        validations.add(
                new ValidationFieldBuilder<CreateConsumptionTableCommand>("name")
                        .required()
                        .minLength(2)
                        .maxLength(255)
                        .build()
        );

        final Validation<CreateConsumptionTableCommand> validator = new ValidationComposite<>(
                validations,
                errorsContainer
        );
        validator.validate(data);
    }

}
