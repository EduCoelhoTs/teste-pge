package br.com.testpge.restaurantmanager.consumptiontable.usecases.dtos;

import java.time.LocalDateTime;

import br.com.testpge.restaurantmanager.consumptiontable.entities.ConsumptionTableEntity;
import br.com.testpge.restaurantmanager.consumptiontable.enums.ConsumptionTableStatus;

public record ConsumptionTableOutput(
        String id,
        LocalDateTime createdAt,
        String name,
        ConsumptionTableStatus status) {

    public static ConsumptionTableOutput fromEntity(ConsumptionTableEntity entity) {
        return new ConsumptionTableOutput(
                entity.getId().toString(),
                entity.getCreatedAt().toLocalDateTime(),
                entity.getName(),
                entity.getStatus()
        );
    }
}
