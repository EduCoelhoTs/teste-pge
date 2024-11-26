package br.com.testpge.restaurantmanager.menu.usecases.dtos;

import br.com.testpge.restaurantmanager.menu.enums.MenuItemType;

public record CreateMenuItemCommand(
        String name,
        String description,
        MenuItemType type,
        Double price) {

}
