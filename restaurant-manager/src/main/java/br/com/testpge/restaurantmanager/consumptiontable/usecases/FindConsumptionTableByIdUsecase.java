package br.com.testpge.restaurantmanager.consumptiontable.usecases;

import br.com.testpge.restaurantmanager.consumptiontable.usecases.dtos.ConsumptionTableOutput;
import br.com.testpge.restaurantmanager.consumptiontable.usecases.dtos.FindConsumptionTableByIdInput;
import br.com.testpge.restaurantmanager.shared.usecases.UseCase;

public interface FindConsumptionTableByIdUsecase
        extends UseCase<FindConsumptionTableByIdInput, ConsumptionTableOutput> {

}
