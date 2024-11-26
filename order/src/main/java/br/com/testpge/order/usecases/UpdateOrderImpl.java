package br.com.testpge.order.usecases;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.kafka.core.KafkaTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.testpge.order.entities.OrderEntity;
import br.com.testpge.order.enums.OrderStatus;
import br.com.testpge.order.repositories.OrderRepository;
import br.com.testpge.order.shared.constants.ErrorMessages;
import br.com.testpge.order.shared.events.EventsStreamTopcs;
import br.com.testpge.order.shared.exceptions.BadRequestException;
import br.com.testpge.order.shared.exceptions.ErrorsContainer;
import br.com.testpge.order.shared.validations.Validation;
import br.com.testpge.order.shared.validations.ValidationComposite;
import br.com.testpge.order.shared.validations.ValidationFieldBuilder;
import br.com.testpge.order.usecases.dtos.OrderStatusChangeEventPayload;
import br.com.testpge.order.usecases.dtos.UpdateOrderCommand;

public class UpdateOrderImpl implements UpdateOrderUsecase {

    private final OrderRepository repository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper mapper;

    public UpdateOrderImpl(
            OrderRepository repository,
            KafkaTemplate<String, String> kafkaTemplate
    ) {
        this.repository = Objects.requireNonNull(repository);
        this.kafkaTemplate = Objects.requireNonNull(kafkaTemplate);
        this.mapper = new ObjectMapper();
    }

    @Override
    public void execute(UpdateOrderCommand data) throws Exception {
        this.validateUpdateOrderCommand(data);

        final Optional<OrderEntity> result = this.repository.findById(
                UUID.fromString(data.id())
        );
        if (result.isEmpty()) {
            throw new BadRequestException(ErrorMessages.notFoundEntityById(
                    "pedido"
            ));
        }

        final OrderEntity entity = result.get();
        entity.setStatus(data.status());
        this.repository.save(entity);
        this.sendEvent(entity);
    }

    private void validateUpdateOrderCommand(
            UpdateOrderCommand data
    ) throws Exception {
        final var errorsContainer = new ErrorsContainer();
        final Set<Validation<UpdateOrderCommand>> validations = new HashSet<>();

        validations.add(
                new ValidationFieldBuilder<UpdateOrderCommand>("id")
                        .required()
                        .uuid()
                        .build()
        );
        validations.add(
                new ValidationFieldBuilder<UpdateOrderCommand>("status")
                        .required()
                        .enumToCompare(OrderStatus.class)
                        .build()
        );

        final Validation<UpdateOrderCommand> validator = new ValidationComposite<>(
                validations,
                errorsContainer
        );
        validator.validate(data);
    }

    private void sendEvent(OrderEntity value) throws Exception {
        final var payload = new OrderStatusChangeEventPayload(
                value.getId().toString(),
                value.getStatus(),
                value.getConsumptionTableId()
        );
        final var stringfiedValue = mapper.writeValueAsString(payload);
        final String key = UUID.randomUUID().toString();

        kafkaTemplate.send(
                EventsStreamTopcs.ORDER_STATUS_CHANGE,
                key,
                stringfiedValue
        );
    }
}
