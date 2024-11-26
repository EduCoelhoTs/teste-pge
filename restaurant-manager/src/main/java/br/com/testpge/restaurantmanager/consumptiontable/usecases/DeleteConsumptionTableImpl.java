package br.com.testpge.restaurantmanager.consumptiontable.usecases;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import br.com.testpge.restaurantmanager.consumptiontable.entities.ConsumptionTableEntity;
import br.com.testpge.restaurantmanager.consumptiontable.repositories.ConsumptionTableRepository;
import br.com.testpge.restaurantmanager.consumptiontable.usecases.dtos.DeleteConsumptionTableCommand;
import br.com.testpge.restaurantmanager.shared.constants.ErrorMessages;
import br.com.testpge.restaurantmanager.shared.exceptions.BadRequestException;
import br.com.testpge.restaurantmanager.shared.exceptions.ErrorsContainer;
import br.com.testpge.restaurantmanager.shared.validations.Validation;
import br.com.testpge.restaurantmanager.shared.validations.ValidationComposite;
import br.com.testpge.restaurantmanager.shared.validations.ValidationFieldBuilder;

public class DeleteConsumptionTableImpl implements DeleteConsumptionTableUsecase {

    private final ConsumptionTableRepository repository;

    public DeleteConsumptionTableImpl(ConsumptionTableRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public void execute(DeleteConsumptionTableCommand input) throws Exception {
        this.validateDeleteConsumptionTableCommand(input);

        final Optional<ConsumptionTableEntity> result = this.repository.findById(
                UUID.fromString(input.id())
        );
        if (result.isEmpty()) {
            throw new BadRequestException(ErrorMessages.notFoundEntityById(
                    "mesa"
            ));
        }

        final ConsumptionTableEntity entity = result.get();
        this.repository.delete(entity);
    }

    private void validateDeleteConsumptionTableCommand(
            DeleteConsumptionTableCommand data
    ) throws Exception {
        final var errorsContainer = new ErrorsContainer();
        final Set<Validation<DeleteConsumptionTableCommand>> validations = new HashSet<>();

        validations.add(
                new ValidationFieldBuilder<DeleteConsumptionTableCommand>("id")
                        .required()
                        .uuid()
                        .build()
        );

        final Validation<DeleteConsumptionTableCommand> validator = new ValidationComposite<>(
                validations,
                errorsContainer
        );
        validator.validate(data);
    }
}
