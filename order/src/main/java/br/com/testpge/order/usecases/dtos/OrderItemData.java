package br.com.testpge.order.usecases.dtos;

import br.com.testpge.order.entities.OrderItemEntity;
import br.com.testpge.order.enums.OrderItemType;

public record OrderItemData(
        String id,
        String name,
        String description,
        OrderItemType type,
        Double price) {

    public static OrderItemData fromEntity(OrderItemEntity entity) {
        return new OrderItemData(
                entity.getId().toString(),
                entity.getName(),
                entity.getDescription(),
                entity.getType(),
                entity.getPrice()
        );
    }
}
