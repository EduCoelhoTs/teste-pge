package br.com.testpge.order.controllers.dtos;

import java.util.List;

import br.com.testpge.order.usecases.dtos.CreateOrderItemData;

public record CreateOrderDto(
        String consumptionTableId,
        List<CreateOrderItemData> orderItens) {

}
