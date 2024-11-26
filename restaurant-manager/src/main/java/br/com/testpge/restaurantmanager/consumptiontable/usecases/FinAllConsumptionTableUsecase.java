package br.com.testpge.restaurantmanager.consumptiontable.usecases;

import java.util.List;

import br.com.testpge.restaurantmanager.consumptiontable.usecases.dtos.ConsumptionTableOutput;
import br.com.testpge.restaurantmanager.shared.usecases.OutOnlyUseCase;

public interface FinAllConsumptionTableUsecase
        extends OutOnlyUseCase<List<ConsumptionTableOutput>> {

}
