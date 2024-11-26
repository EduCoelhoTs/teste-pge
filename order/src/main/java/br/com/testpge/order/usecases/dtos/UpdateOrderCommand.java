package br.com.testpge.order.usecases.dtos;

import br.com.testpge.order.enums.OrderStatus;

public record UpdateOrderCommand(
        String id,
        OrderStatus status) {

}
