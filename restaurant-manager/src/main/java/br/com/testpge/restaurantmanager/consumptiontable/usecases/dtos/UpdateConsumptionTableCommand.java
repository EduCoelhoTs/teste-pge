package br.com.testpge.restaurantmanager.consumptiontable.usecases.dtos;

import br.com.testpge.restaurantmanager.consumptiontable.enums.ConsumptionTableStatus;

public record UpdateConsumptionTableCommand(
        String id,
        String name,
        ConsumptionTableStatus status) {

}
