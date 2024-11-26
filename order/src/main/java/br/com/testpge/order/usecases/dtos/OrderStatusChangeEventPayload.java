package br.com.testpge.order.usecases.dtos;

import br.com.testpge.order.enums.OrderStatus;

public record OrderStatusChangeEventPayload(
        String id,
        OrderStatus status,
        String consumptionTableId) {

}
