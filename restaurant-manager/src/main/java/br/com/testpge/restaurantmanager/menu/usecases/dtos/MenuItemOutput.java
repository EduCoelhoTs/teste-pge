package br.com.testpge.restaurantmanager.menu.usecases.dtos;

import java.time.LocalDateTime;

import br.com.testpge.restaurantmanager.menu.entities.MenuItemEntity;
import br.com.testpge.restaurantmanager.menu.enums.MenuItemType;

public record MenuItemOutput(
        String id,
        LocalDateTime createdAt,
        String name,
        MenuItemType type,
        String description,
        Double price) {

    public static MenuItemOutput fromEntity(MenuItemEntity entity) {
        return new MenuItemOutput(
                entity.getId().toString(),
                entity.getCreatedAt().toLocalDateTime(),
                entity.getName(),
                entity.getType(),
                entity.getDescription(),
                entity.getPrice()
        );
    }
}
