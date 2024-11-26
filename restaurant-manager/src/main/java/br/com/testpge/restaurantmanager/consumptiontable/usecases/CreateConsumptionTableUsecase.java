package br.com.testpge.restaurantmanager.consumptiontable.usecases;

import br.com.testpge.restaurantmanager.consumptiontable.usecases.dtos.CreateConsumptionTableCommand;
import br.com.testpge.restaurantmanager.shared.data.dtos.EntityIdOutput;
import br.com.testpge.restaurantmanager.shared.usecases.UseCase;

public interface CreateConsumptionTableUsecase
        extends UseCase<CreateConsumptionTableCommand, EntityIdOutput> {

}
