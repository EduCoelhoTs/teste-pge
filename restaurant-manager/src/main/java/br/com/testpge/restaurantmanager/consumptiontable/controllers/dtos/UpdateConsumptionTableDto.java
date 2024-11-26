package br.com.testpge.restaurantmanager.consumptiontable.controllers.dtos;

import br.com.testpge.restaurantmanager.consumptiontable.enums.ConsumptionTableStatus;

public record UpdateConsumptionTableDto(
        String id,
        String name,
        ConsumptionTableStatus status) {

}
