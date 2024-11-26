package br.com.testpge.order.usecases;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import org.springframework.kafka.core.KafkaTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.testpge.order.entities.OrderEntity;
import br.com.testpge.order.entities.OrderItemEntity;
import br.com.testpge.order.enums.ConsumptionTableStatus;
import br.com.testpge.order.enums.OrderItemType;
import br.com.testpge.order.enums.OrderStatus;
import br.com.testpge.order.repositories.OrderRepository;
import br.com.testpge.order.shared.data.dtos.EntityIdOutput;
import br.com.testpge.order.shared.events.EventsStreamTopcs;
import br.com.testpge.order.shared.exceptions.BadRequestException;
import br.com.testpge.order.shared.exceptions.ErrorsContainer;
import br.com.testpge.order.shared.httpclient.RestaurantApiClient;
import br.com.testpge.order.shared.httpclient.dtos.ConsumptionTableDto;
import br.com.testpge.order.shared.validations.Validation;
import br.com.testpge.order.shared.validations.ValidationComposite;
import br.com.testpge.order.shared.validations.ValidationFieldBuilder;
import br.com.testpge.order.usecases.dtos.CreateOrderCommand;
import br.com.testpge.order.usecases.dtos.CreateOrderItemData;
import br.com.testpge.order.usecases.dtos.OrderStatusChangeEventPayload;

public class CreateOrderImpl implements CreateOrderUsecase {

    private final OrderRepository repository;
    private final RestaurantApiClient restaurantApiClient;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper mapper;

    public CreateOrderImpl(
            OrderRepository repository,
            RestaurantApiClient restaurantApiClient,
            KafkaTemplate<String, String> kafkaTemplate
    ) {
        this.repository = Objects.requireNonNull(repository);
        this.restaurantApiClient = Objects.requireNonNull(restaurantApiClient);
        this.kafkaTemplate = Objects.requireNonNull(kafkaTemplate);
        this.mapper = new ObjectMapper();
    }

    @Override
    public EntityIdOutput execute(CreateOrderCommand data) throws Exception {
        this.validateCreateOrderCommand(data);

        final ConsumptionTableDto consumptionTable = this.restaurantApiClient
                .findConsumptionTableById(data.consumptionTableId());
        if (consumptionTable.status() != ConsumptionTableStatus.FREE) {
            throw new BadRequestException("mesa ocupada");
        }

        final OderItemEntityListAndTotal oderItemEntityListAndTotal = this
                .getOderItemEntityListAndTotal(data.orderItens());
        final var entity = new OrderEntity();
        entity.setId(UUID.randomUUID());
        entity.setConsumptionTableId(consumptionTable.id());
        entity.setStatus(OrderStatus.ORDER_REQUESTED);
        entity.setTotal(oderItemEntityListAndTotal.total());
        entity.setOrderItens(oderItemEntityListAndTotal.orderItens());
        this.repository.save(entity);
        this.sendEvent(entity);

        return new EntityIdOutput(entity.getId().toString());
    }

    private OderItemEntityListAndTotal getOderItemEntityListAndTotal(List<CreateOrderItemData> data) throws Exception {
        final Set<OrderItemEntity> orderItens = new HashSet<>();
        Double formattedTotal = 0.0;
        for (final CreateOrderItemData item : data) {
            this.validateOrderItemData(item);

            final var orderItemEntity = new OrderItemEntity();
            orderItemEntity.setId(UUID.randomUUID());
            orderItemEntity.setName(item.name());
            orderItemEntity.setType(item.type());
            orderItemEntity.setDescription(item.description());
            orderItemEntity.setPrice(item.price());

            orderItens.add(orderItemEntity);
            formattedTotal = formattedTotal + (item.price() * 100);
        }

        final Double total = formattedTotal / 100;

        return new OderItemEntityListAndTotal(orderItens, total);
    }

    private void validateCreateOrderCommand(
            CreateOrderCommand data
    ) throws Exception {
        final var errorsContainer = new ErrorsContainer();
        final Set<Validation<CreateOrderCommand>> validations = new HashSet<>();

        validations.add(
                new ValidationFieldBuilder<CreateOrderCommand>("consumptionTableId")
                        .required()
                        .uuid()
                        .build()
        );

        final Validation<CreateOrderCommand> validator = new ValidationComposite<>(
                validations,
                errorsContainer
        );
        validator.validate(data);
    }

    private void validateOrderItemData(
            CreateOrderItemData data
    ) throws Exception {
        final var errorsContainer = new ErrorsContainer();
        final Set<Validation<CreateOrderItemData>> validations = new HashSet<>();

        validations.add(
                new ValidationFieldBuilder<CreateOrderItemData>("name")
                        .required()
                        .minLength(2)
                        .maxLength(255)
                        .build()
        );
        validations.add(
                new ValidationFieldBuilder<CreateOrderItemData>("description")
                        .required()
                        .minLength(2)
                        .maxLength(255)
                        .build()
        );
        validations.add(
                new ValidationFieldBuilder<CreateOrderItemData>("price")
                        .required()
                        .minValue(0.01)
                        .maxValue(10000000.0)
                        .build()
        );
        validations.add(
                new ValidationFieldBuilder<CreateOrderItemData>("type")
                        .required()
                        .enumToCompare(OrderItemType.class)
                        .build()
        );

        final Validation<CreateOrderItemData> validator = new ValidationComposite<>(
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

record OderItemEntityListAndTotal(
        Set<OrderItemEntity> orderItens,
        Double total) {

}
