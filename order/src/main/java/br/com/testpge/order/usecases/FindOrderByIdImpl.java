package br.com.testpge.order.usecases;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import br.com.testpge.order.entities.OrderEntity;
import br.com.testpge.order.repositories.OrderRepository;
import br.com.testpge.order.shared.constants.ErrorMessages;
import br.com.testpge.order.shared.exceptions.ErrorsContainer;
import br.com.testpge.order.shared.exceptions.NotFoundException;
import br.com.testpge.order.shared.validations.Validation;
import br.com.testpge.order.shared.validations.ValidationComposite;
import br.com.testpge.order.shared.validations.ValidationFieldBuilder;
import br.com.testpge.order.usecases.dtos.FindOrderByIdInput;
import br.com.testpge.order.usecases.dtos.OrderOutput;

public class FindOrderByIdImpl implements FindOrderByIdUsecase {

    private final OrderRepository repository;

    public FindOrderByIdImpl(OrderRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public OrderOutput execute(FindOrderByIdInput data) throws Exception {
        this.validateFindOrderByIdInput(data);

        final Optional<OrderEntity> result = this.repository.findById(
                UUID.fromString(data.id())
        );
        if (result.isEmpty()) {
            throw new NotFoundException(ErrorMessages.notFoundEntityById(
                    "pedido"
            ));
        }

        return OrderOutput.fromEntity(result.get());
    }

    private void validateFindOrderByIdInput(
            FindOrderByIdInput data
    ) throws Exception {
        final var errorsContainer = new ErrorsContainer();
        final Set<Validation<FindOrderByIdInput>> validations = new HashSet<>();

        validations.add(
                new ValidationFieldBuilder<FindOrderByIdInput>("id")
                        .required()
                        .uuid()
                        .build()
        );

        final Validation<FindOrderByIdInput> validator = new ValidationComposite<>(
                validations,
                errorsContainer
        );
        validator.validate(data);
    }

}
