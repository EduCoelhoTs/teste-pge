package br.com.testpge.order.usecases.dtos;

import java.time.LocalDateTime;
import java.util.List;

import br.com.testpge.order.entities.OrderEntity;
import br.com.testpge.order.enums.OrderStatus;

public record OrderOutput(
        String id,
        OrderStatus status,
        Double total,
        String consumptionTableId,
        LocalDateTime updatedAt,
        LocalDateTime createdAt,
        List<OrderItemData> orderItens) {

    public static OrderOutput fromEntity(OrderEntity entity) {
        return new OrderOutput(
                entity.getId().toString(),
                entity.getStatus(),
                entity.getTotal(),
                entity.getConsumptionTableId(),
                entity.getCreatedAt().toLocalDateTime(),
                entity.getUpdatedAt().toLocalDateTime(),
                entity.getOrderItens()
                        .stream()
                        .map(OrderItemData::fromEntity)
                        .toList()
        );
    }
}
