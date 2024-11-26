package br.com.testpge.restaurantmanager.menu.usecases;

import java.util.List;
import java.util.Objects;

import br.com.testpge.restaurantmanager.menu.entities.MenuItemEntity;
import br.com.testpge.restaurantmanager.menu.repositories.MenuItemRepository;
import br.com.testpge.restaurantmanager.menu.usecases.dtos.FindMenuItemByTypeInput;
import br.com.testpge.restaurantmanager.menu.usecases.dtos.MenuItemOutput;

public class FinAllMenuItemByTypeImpl implements FinAllMenuItemByTypeUsecase {

    private final MenuItemRepository repository;

    public FinAllMenuItemByTypeImpl(MenuItemRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public List<MenuItemOutput> execute(FindMenuItemByTypeInput input) throws Exception {

        final List<MenuItemEntity> result = this.repository.findByType(input.type());

        return result
                .stream()
                .map(item -> MenuItemOutput.fromEntity(item))
                .toList();
    }
}
