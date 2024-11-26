package br.com.testpge.order.usecases;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import br.com.testpge.order.entities.OrderEntity;
import br.com.testpge.order.repositories.OrderRepository;
import br.com.testpge.order.shared.constants.ErrorMessages;
import br.com.testpge.order.shared.exceptions.BadRequestException;
import br.com.testpge.order.shared.exceptions.ErrorsContainer;
import br.com.testpge.order.shared.validations.Validation;
import br.com.testpge.order.shared.validations.ValidationComposite;
import br.com.testpge.order.shared.validations.ValidationFieldBuilder;
import br.com.testpge.order.usecases.dtos.DeleteOrderCommand;

public class DeleteOrderImpl implements DeleteOrderUsecase {

    private final OrderRepository repository;

    public DeleteOrderImpl(OrderRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public void execute(DeleteOrderCommand input) throws Exception {
        this.validateDeleteOrderCommand(input);

        final Optional<OrderEntity> result = this.repository.findById(
                UUID.fromString(input.id())
        );
        if (result.isEmpty()) {
            throw new BadRequestException(ErrorMessages.notFoundEntityById(
                    "pedido"
            ));
        }

        final OrderEntity entity = result.get();
        this.repository.delete(entity);
    }

    private void validateDeleteOrderCommand(
            DeleteOrderCommand data
    ) throws Exception {
        final var errorsContainer = new ErrorsContainer();
        final Set<Validation<DeleteOrderCommand>> validations = new HashSet<>();

        validations.add(
                new ValidationFieldBuilder<DeleteOrderCommand>("id")
                        .required()
                        .uuid()
                        .build()
        );

        final Validation<DeleteOrderCommand> validator = new ValidationComposite<>(
                validations,
                errorsContainer
        );
        validator.validate(data);
    }
}
