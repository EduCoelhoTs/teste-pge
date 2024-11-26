package br.com.testpge.order.controllers.dtos;

import br.com.testpge.order.enums.OrderStatus;

public record UpdateOrderDto(
        String id,
        OrderStatus status) {

}
