package br.com.testpge.order.usecases.dtos;

import java.util.List;

public record CreateOrderCommand(
        String consumptionTableId,
        List<CreateOrderItemData> orderItens) {

}
