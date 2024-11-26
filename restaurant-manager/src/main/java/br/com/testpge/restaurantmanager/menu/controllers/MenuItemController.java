package br.com.testpge.restaurantmanager.menu.controllers;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.testpge.restaurantmanager.menu.controllers.api.MenuItemApi;
import br.com.testpge.restaurantmanager.menu.controllers.dtos.CreateMenuItemDto;
import br.com.testpge.restaurantmanager.menu.controllers.dtos.UpdateMenuItemDto;
import br.com.testpge.restaurantmanager.menu.enums.MenuItemType;
import br.com.testpge.restaurantmanager.menu.usecases.CreateMenuItemUsecase;
import br.com.testpge.restaurantmanager.menu.usecases.DeleteMenuItemUsecase;
import br.com.testpge.restaurantmanager.menu.usecases.FinAllMenuItemByTypeUsecase;
import br.com.testpge.restaurantmanager.menu.usecases.FindMenuItemByIdUsecase;
import br.com.testpge.restaurantmanager.menu.usecases.UpdateMenuItemUsecase;
import br.com.testpge.restaurantmanager.menu.usecases.dtos.CreateMenuItemCommand;
import br.com.testpge.restaurantmanager.menu.usecases.dtos.DeleteMenuItemCommand;
import br.com.testpge.restaurantmanager.menu.usecases.dtos.FindMenuItemByIdInput;
import br.com.testpge.restaurantmanager.menu.usecases.dtos.FindMenuItemByTypeInput;
import br.com.testpge.restaurantmanager.menu.usecases.dtos.MenuItemOutput;
import br.com.testpge.restaurantmanager.menu.usecases.dtos.UpdateMenuItemCommand;
import br.com.testpge.restaurantmanager.shared.data.dtos.EntityIdOutput;

@RestController
public class MenuItemController implements MenuItemApi {

    private final CreateMenuItemUsecase createMenuItemUsecase;
    private final UpdateMenuItemUsecase updateMenuItemUsecase;
    private final FindMenuItemByIdUsecase findMenuItemByIdUsecase;
    private final FinAllMenuItemByTypeUsecase finAllMenuItemByTypeUsecase;
    private final DeleteMenuItemUsecase deleteMenuItemUsecase;

    public MenuItemController(
            CreateMenuItemUsecase createMenuItemUsecase,
            UpdateMenuItemUsecase updateMenuItemUsecase,
            FindMenuItemByIdUsecase findMenuItemByIdUsecase,
            FinAllMenuItemByTypeUsecase finAllMenuItemByTypeUsecase,
            DeleteMenuItemUsecase deleteMenuItemUsecase
    ) {
        this.createMenuItemUsecase = Objects.requireNonNull(createMenuItemUsecase);
        this.updateMenuItemUsecase = Objects.requireNonNull(updateMenuItemUsecase);
        this.findMenuItemByIdUsecase = Objects.requireNonNull(findMenuItemByIdUsecase);
        this.finAllMenuItemByTypeUsecase = Objects.requireNonNull(finAllMenuItemByTypeUsecase);
        this.deleteMenuItemUsecase = Objects.requireNonNull(deleteMenuItemUsecase);
    }

    @Override
    public ResponseEntity<EntityIdOutput> createMenuItem(CreateMenuItemDto request) throws Exception {
        final var input = new CreateMenuItemCommand(
                request.name(),
                request.description(),
                request.type(),
                request.price()
        );
        final EntityIdOutput output = this.createMenuItemUsecase.execute(input);

        return ResponseEntity.status(HttpStatus.CREATED).body(output);
    }

    @Override
    public ResponseEntity<?> updateMenuItem(UpdateMenuItemDto request) throws Exception {
        final var input = new UpdateMenuItemCommand(
                request.id(),
                request.name(),
                request.type(),
                request.description(),
                request.price()
        );
        this.updateMenuItemUsecase.execute(input);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<MenuItemOutput> findMenuItemById(String id) throws Exception {
        final var input = new FindMenuItemByIdInput(id);
        final MenuItemOutput output = this.findMenuItemByIdUsecase.execute(input);

        return ResponseEntity.status(HttpStatus.OK).body(output);
    }

    @Override
    public ResponseEntity<List<MenuItemOutput>> findMenuItems(MenuItemType type) throws Exception {
        final var input = new FindMenuItemByTypeInput(type);
        final List<MenuItemOutput> output = this.finAllMenuItemByTypeUsecase.execute(input);

        return ResponseEntity.status(HttpStatus.OK).body(output);
    }

    @Override
    public ResponseEntity<?> deleteMenuItem(String id) throws Exception {
        final var input = new DeleteMenuItemCommand(id);
        this.deleteMenuItemUsecase.execute(input);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
