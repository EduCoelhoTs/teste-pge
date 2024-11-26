package br.com.testpge.restaurantmanager.consumptiontable.usecases;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import br.com.testpge.restaurantmanager.consumptiontable.entities.ConsumptionTableEntity;
import br.com.testpge.restaurantmanager.consumptiontable.enums.ConsumptionTableStatus;
import br.com.testpge.restaurantmanager.consumptiontable.repositories.ConsumptionTableRepository;
import br.com.testpge.restaurantmanager.consumptiontable.usecases.dtos.UpdateConsumptionTableCommand;
import br.com.testpge.restaurantmanager.shared.constants.ErrorMessages;
import br.com.testpge.restaurantmanager.shared.exceptions.BadRequestException;
import br.com.testpge.restaurantmanager.shared.exceptions.ErrorsContainer;
import br.com.testpge.restaurantmanager.shared.validations.Validation;
import br.com.testpge.restaurantmanager.shared.validations.ValidationComposite;
import br.com.testpge.restaurantmanager.shared.validations.ValidationFieldBuilder;

public class UpdateConsumptionTableImpl implements UpdateConsumptionTableUsecase {

    private final ConsumptionTableRepository repository;

    public UpdateConsumptionTableImpl(ConsumptionTableRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public void execute(UpdateConsumptionTableCommand data) throws Exception {
        this.validateUpdateConsumptionTableCommand(data);

        final Optional<ConsumptionTableEntity> result = this.repository.findById(
                UUID.fromString(data.id())
        );
        if (result.isEmpty()) {
            throw new BadRequestException(ErrorMessages.notFoundEntityById(
                    "mesa"
            ));
        }

        final ConsumptionTableEntity entity = result.get();
        entity.setName(data.name());
        entity.setStatus(data.status());
        this.repository.save(entity);
    }

    private void validateUpdateConsumptionTableCommand(
            UpdateConsumptionTableCommand data
    ) throws Exception {
        final var errorsContainer = new ErrorsContainer();
        final Set<Validation<UpdateConsumptionTableCommand>> validations = new HashSet<>();

        validations.add(
                new ValidationFieldBuilder<UpdateConsumptionTableCommand>("id")
                        .required()
                        .uuid()
                        .build()
        );
        validations.add(
                new ValidationFieldBuilder<UpdateConsumptionTableCommand>("name")
                        .required()
                        .minLength(2)
                        .maxLength(255)
                        .build()
        );
        validations.add(
                new ValidationFieldBuilder<UpdateConsumptionTableCommand>("status")
                        .required()
                        .enumToCompare(ConsumptionTableStatus.class)
                        .build()
        );

        final Validation<UpdateConsumptionTableCommand> validator = new ValidationComposite<>(
                validations,
                errorsContainer
        );
        validator.validate(data);
    }

}
