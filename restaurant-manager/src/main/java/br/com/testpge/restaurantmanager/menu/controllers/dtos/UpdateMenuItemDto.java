package br.com.testpge.restaurantmanager.menu.controllers.dtos;

import br.com.testpge.restaurantmanager.menu.enums.MenuItemType;

public record UpdateMenuItemDto(
        String id,
        String name,
        MenuItemType type,
        String description,
        Double price) {

}
