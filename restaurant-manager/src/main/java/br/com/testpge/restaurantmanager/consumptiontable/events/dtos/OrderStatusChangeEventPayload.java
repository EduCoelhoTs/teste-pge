package br.com.testpge.restaurantmanager.consumptiontable.events.dtos;

import br.com.testpge.restaurantmanager.consumptiontable.enums.OrderStatus;

public record OrderStatusChangeEventPayload(
        String id,
        OrderStatus status,
        String consumptionTableId) {

}
