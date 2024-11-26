package br.com.testpge.restaurantmanager.menu.controllers.dtos;

import br.com.testpge.restaurantmanager.menu.enums.MenuItemType;

public record CreateMenuItemDto(String name,
        String description,
        MenuItemType type,
        Double price) {

}
