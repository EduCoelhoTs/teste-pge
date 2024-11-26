package br.com.testpge.order.shared.httpclient.dtos;

import java.time.LocalDateTime;

import br.com.testpge.order.enums.ConsumptionTableStatus;

public record ConsumptionTableDto(
        String id,
        LocalDateTime createdAt,
        String name,
        ConsumptionTableStatus status) {

}
