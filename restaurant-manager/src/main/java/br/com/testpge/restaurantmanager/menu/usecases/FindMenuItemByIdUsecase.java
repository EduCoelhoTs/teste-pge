package br.com.testpge.restaurantmanager.menu.usecases;

import br.com.testpge.restaurantmanager.menu.usecases.dtos.FindMenuItemByIdInput;
import br.com.testpge.restaurantmanager.menu.usecases.dtos.MenuItemOutput;
import br.com.testpge.restaurantmanager.shared.usecases.UseCase;

public interface FindMenuItemByIdUsecase
        extends UseCase<FindMenuItemByIdInput, MenuItemOutput> {

}
