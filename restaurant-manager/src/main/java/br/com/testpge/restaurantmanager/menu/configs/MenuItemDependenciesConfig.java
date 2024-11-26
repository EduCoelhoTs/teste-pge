package br.com.testpge.restaurantmanager.menu.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.testpge.restaurantmanager.menu.repositories.MenuItemRepository;
import br.com.testpge.restaurantmanager.menu.usecases.CreateMenuItemImpl;
import br.com.testpge.restaurantmanager.menu.usecases.CreateMenuItemUsecase;
import br.com.testpge.restaurantmanager.menu.usecases.DeleteMenuItemImpl;
import br.com.testpge.restaurantmanager.menu.usecases.DeleteMenuItemUsecase;
import br.com.testpge.restaurantmanager.menu.usecases.FinAllMenuItemByTypeImpl;
import br.com.testpge.restaurantmanager.menu.usecases.FinAllMenuItemByTypeUsecase;
import br.com.testpge.restaurantmanager.menu.usecases.FindMenuItemByIdImpl;
import br.com.testpge.restaurantmanager.menu.usecases.FindMenuItemByIdUsecase;
import br.com.testpge.restaurantmanager.menu.usecases.UpdateMenuItemImpl;
import br.com.testpge.restaurantmanager.menu.usecases.UpdateMenuItemUsecase;

@Configuration
public class MenuItemDependenciesConfig {

    @Bean
    public CreateMenuItemUsecase createMenuItemUsecase(
            MenuItemRepository repository
    ) {
        return new CreateMenuItemImpl(repository);
    }

    @Bean
    public FindMenuItemByIdUsecase findMenuItemByIdUsecase(
            MenuItemRepository repository
    ) {
        return new FindMenuItemByIdImpl(repository);
    }

    @Bean
    public UpdateMenuItemUsecase updateMenuItemUsecase(
            MenuItemRepository repository
    ) {
        return new UpdateMenuItemImpl(repository);
    }

    @Bean
    public FinAllMenuItemByTypeUsecase finAllMenuItemUsecase(
            MenuItemRepository repository
    ) {
        return new FinAllMenuItemByTypeImpl(repository);
    }

    @Bean
    public DeleteMenuItemUsecase deleteMenuItemUsecase(
            MenuItemRepository repository
    ) {
        return new DeleteMenuItemImpl(repository);
    }
}
