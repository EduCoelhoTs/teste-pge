package br.com.testpge.restaurantmanager.menu.usecases;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import br.com.testpge.restaurantmanager.menu.entities.MenuItemEntity;
import br.com.testpge.restaurantmanager.menu.repositories.MenuItemRepository;
import br.com.testpge.restaurantmanager.menu.usecases.dtos.FindMenuItemByIdInput;
import br.com.testpge.restaurantmanager.menu.usecases.dtos.MenuItemOutput;
import br.com.testpge.restaurantmanager.shared.constants.ErrorMessages;
import br.com.testpge.restaurantmanager.shared.exceptions.ErrorsContainer;
import br.com.testpge.restaurantmanager.shared.exceptions.NotFoundException;
import br.com.testpge.restaurantmanager.shared.validations.Validation;
import br.com.testpge.restaurantmanager.shared.validations.ValidationComposite;
import br.com.testpge.restaurantmanager.shared.validations.ValidationFieldBuilder;

public class FindMenuItemByIdImpl implements FindMenuItemByIdUsecase {

    private final MenuItemRepository repository;

    public FindMenuItemByIdImpl(MenuItemRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public MenuItemOutput execute(FindMenuItemByIdInput data) throws Exception {
        this.validateFindMenuItemByIdInput(data);

        final Optional<MenuItemEntity> result = this.repository.findById(
                UUID.fromString(data.id())
        );
        if (result.isEmpty()) {
            throw new NotFoundException(ErrorMessages.notFoundEntityById(
                    "item do menu"
            ));
        }

        return MenuItemOutput.fromEntity(result.get());
    }

    private void validateFindMenuItemByIdInput(
            FindMenuItemByIdInput data
    ) throws Exception {
        final var errorsContainer = new ErrorsContainer();
        final Set<Validation<FindMenuItemByIdInput>> validations = new HashSet<>();

        validations.add(
                new ValidationFieldBuilder<FindMenuItemByIdInput>("id")
                        .required()
                        .uuid()
                        .build()
        );

        final Validation<FindMenuItemByIdInput> validator = new ValidationComposite<>(
                validations,
                errorsContainer
        );
        validator.validate(data);
    }

}
