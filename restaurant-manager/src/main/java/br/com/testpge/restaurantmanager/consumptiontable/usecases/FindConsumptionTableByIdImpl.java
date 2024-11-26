package br.com.testpge.restaurantmanager.consumptiontable.usecases;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import br.com.testpge.restaurantmanager.consumptiontable.entities.ConsumptionTableEntity;
import br.com.testpge.restaurantmanager.consumptiontable.repositories.ConsumptionTableRepository;
import br.com.testpge.restaurantmanager.consumptiontable.usecases.dtos.ConsumptionTableOutput;
import br.com.testpge.restaurantmanager.consumptiontable.usecases.dtos.FindConsumptionTableByIdInput;
import br.com.testpge.restaurantmanager.shared.constants.ErrorMessages;
import br.com.testpge.restaurantmanager.shared.exceptions.ErrorsContainer;
import br.com.testpge.restaurantmanager.shared.exceptions.NotFoundException;
import br.com.testpge.restaurantmanager.shared.validations.Validation;
import br.com.testpge.restaurantmanager.shared.validations.ValidationComposite;
import br.com.testpge.restaurantmanager.shared.validations.ValidationFieldBuilder;

public class FindConsumptionTableByIdImpl implements FindConsumptionTableByIdUsecase {

    private final ConsumptionTableRepository repository;

    public FindConsumptionTableByIdImpl(ConsumptionTableRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public ConsumptionTableOutput execute(FindConsumptionTableByIdInput data) throws Exception {
        this.validateFindConsumptionTableByIdInput(data);

        final Optional<ConsumptionTableEntity> result = this.repository.findById(
                UUID.fromString(data.id())
        );
        if (result.isEmpty()) {
            throw new NotFoundException(ErrorMessages.notFoundEntityById(
                    "mesa"
            ));
        }

        return ConsumptionTableOutput.fromEntity(result.get());
    }

    private void validateFindConsumptionTableByIdInput(
            FindConsumptionTableByIdInput data
    ) throws Exception {
        final var errorsContainer = new ErrorsContainer();
        final Set<Validation<FindConsumptionTableByIdInput>> validations = new HashSet<>();

        validations.add(
                new ValidationFieldBuilder<FindConsumptionTableByIdInput>("id")
                        .required()
                        .uuid()
                        .build()
        );

        final Validation<FindConsumptionTableByIdInput> validator = new ValidationComposite<>(
                validations,
                errorsContainer
        );
        validator.validate(data);
    }

}
