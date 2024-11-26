package br.com.testpge.restaurantmanager.menu.usecases;

import br.com.testpge.restaurantmanager.menu.usecases.dtos.CreateMenuItemCommand;
import br.com.testpge.restaurantmanager.shared.data.dtos.EntityIdOutput;
import br.com.testpge.restaurantmanager.shared.usecases.UseCase;

public interface CreateMenuItemUsecase
        extends UseCase<CreateMenuItemCommand, EntityIdOutput> {

}
