package br.com.testpge.restaurantmanager.menu.usecases.dtos;

import br.com.testpge.restaurantmanager.menu.enums.MenuItemType;

public record UpdateMenuItemCommand(
        String id,
        String name,
        MenuItemType type,
        String description,
        Double price) {

}
