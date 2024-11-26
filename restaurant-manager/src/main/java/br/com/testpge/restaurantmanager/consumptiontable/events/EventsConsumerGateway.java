package br.com.testpge.restaurantmanager.consumptiontable.events;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.testpge.restaurantmanager.consumptiontable.entities.ConsumptionTableEntity;
import br.com.testpge.restaurantmanager.consumptiontable.enums.ConsumptionTableStatus;
import br.com.testpge.restaurantmanager.consumptiontable.enums.OrderStatus;
import br.com.testpge.restaurantmanager.consumptiontable.events.dtos.OrderStatusChangeEventPayload;
import br.com.testpge.restaurantmanager.consumptiontable.repositories.ConsumptionTableRepository;
import br.com.testpge.restaurantmanager.consumptiontable.usecases.UpdateConsumptionTableUsecase;
import br.com.testpge.restaurantmanager.consumptiontable.usecases.dtos.UpdateConsumptionTableCommand;
import br.com.testpge.restaurantmanager.shared.constants.ErrorMessages;
import br.com.testpge.restaurantmanager.shared.data.ProcessedEventDataGateway;
import br.com.testpge.restaurantmanager.shared.data.ProcessedEventEntity;
import br.com.testpge.restaurantmanager.shared.events.EventsStreamTopcs;
import br.com.testpge.restaurantmanager.shared.exceptions.BadRequestException;
import br.com.testpge.restaurantmanager.shared.exceptions.InternalServerErrorException;

@Component
public class EventsConsumerGateway {

    //  Gson gson = new Gson();
    private final ObjectMapper objectMapper;
    private final UpdateConsumptionTableUsecase updateConsumptionTableUsecase;
    private final ProcessedEventDataGateway processedEventDataGateway;
    private final ConsumptionTableRepository consumptionTableRepository;

    public EventsConsumerGateway(
            ObjectMapper objectMapper,
            UpdateConsumptionTableUsecase updateConsumptionTableUsecase,
            ProcessedEventDataGateway processedEventDataGateway,
            ConsumptionTableRepository consumptionTableRepository
    ) {
        this.objectMapper = objectMapper;
        this.updateConsumptionTableUsecase = Objects.requireNonNull(updateConsumptionTableUsecase);
        this.processedEventDataGateway = Objects.requireNonNull(processedEventDataGateway);
        this.consumptionTableRepository = Objects.requireNonNull(consumptionTableRepository);
    }

    @KafkaListener(
            topics = EventsStreamTopcs.ORDER_STATUS_CHANGE,
            groupId = "restaurant-group"
    )
    public void proposalReceived(
            @Payload ConsumerRecord<String, String> payload
    ) throws Exception {
        try {
            final Optional<ProcessedEventEntity> event = this.processedEventDataGateway
                    .findById(UUID.fromString(payload.key()));
            if (event.isPresent()) {
                throw new BadRequestException("evento já processado");
            }

            String unescapedJson = this.objectMapper.readValue(payload.value(), String.class);
            final OrderStatusChangeEventPayload payloadObject = this.objectMapper.readValue(
                    unescapedJson,
                    OrderStatusChangeEventPayload.class
            );

            this.processConsumptionTableStatus(payloadObject);

            final var processedEventEntity = new ProcessedEventEntity(
                    UUID.fromString(payload.key()),
                    unescapedJson,
                    OffsetDateTime.now(ZoneOffset.UTC),
                    payload.topic()
            );
            this.processedEventDataGateway.save(processedEventEntity);
        } catch (JsonProcessingException e) {
            throw new InternalServerErrorException(e.getMessage());
        } catch (Exception e) {
            if (e instanceof BadRequestException) {
                throw e;

            }

            throw new InternalServerErrorException(e.getMessage());
        }
    }

    private void processConsumptionTableStatus(OrderStatusChangeEventPayload payloadObject) throws Exception {
        final Optional<ConsumptionTableEntity> result = this.consumptionTableRepository.findById(
                UUID.fromString(payloadObject.consumptionTableId())
        );
        if (result.isEmpty()) {
            throw new BadRequestException(ErrorMessages.notFoundEntityById(
                    "mesa"
            ));
        }

        final Boolean orderStatusThatOccupiesTable = payloadObject.status() == OrderStatus.ORDER_IN_PREPARATION
                || payloadObject.status() == OrderStatus.ORDER_REQUESTED
                || payloadObject.status() == OrderStatus.ORDER_READY;
        final Boolean occupiedTable = result.get().getStatus() == ConsumptionTableStatus.OCCUPIED;
        if (orderStatusThatOccupiesTable && occupiedTable) {
            return;
        }

        ConsumptionTableStatus status = ConsumptionTableStatus.FREE;
        if (orderStatusThatOccupiesTable) {
            status = ConsumptionTableStatus.OCCUPIED;
        }

        final var input = new UpdateConsumptionTableCommand(
                payloadObject.consumptionTableId(),
                result.get().getName(),
                status
        );
        this.updateConsumptionTableUsecase.execute(input);
    }
}
