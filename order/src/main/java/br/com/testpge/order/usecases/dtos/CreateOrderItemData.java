package br.com.testpge.order.usecases.dtos;

import br.com.testpge.order.entities.OrderItemEntity;
import br.com.testpge.order.enums.OrderItemType;

public record CreateOrderItemData(
        String name,
        String description,
        OrderItemType type,
        Double price) {

    public static CreateOrderItemData fromEntity(OrderItemEntity entity) {
        return new CreateOrderItemData(
                entity.getName(),
                entity.getDescription(),
                entity.getType(),
                entity.getPrice()
        );
    }
}
