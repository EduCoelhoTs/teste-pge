package br.com.testpge.restaurantmanager.menu.usecases;

import java.util.List;

import br.com.testpge.restaurantmanager.menu.usecases.dtos.FindMenuItemByTypeInput;
import br.com.testpge.restaurantmanager.menu.usecases.dtos.MenuItemOutput;
import br.com.testpge.restaurantmanager.shared.usecases.UseCase;

public interface FinAllMenuItemByTypeUsecase
        extends UseCase<FindMenuItemByTypeInput, List<MenuItemOutput>> {

}
