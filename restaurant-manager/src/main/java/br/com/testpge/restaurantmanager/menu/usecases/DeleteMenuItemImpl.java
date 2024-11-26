package br.com.testpge.restaurantmanager.menu.usecases;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import br.com.testpge.restaurantmanager.menu.entities.MenuItemEntity;
import br.com.testpge.restaurantmanager.menu.repositories.MenuItemRepository;
import br.com.testpge.restaurantmanager.menu.usecases.dtos.DeleteMenuItemCommand;
import br.com.testpge.restaurantmanager.shared.constants.ErrorMessages;
import br.com.testpge.restaurantmanager.shared.exceptions.BadRequestException;
import br.com.testpge.restaurantmanager.shared.exceptions.ErrorsContainer;
import br.com.testpge.restaurantmanager.shared.validations.Validation;
import br.com.testpge.restaurantmanager.shared.validations.ValidationComposite;
import br.com.testpge.restaurantmanager.shared.validations.ValidationFieldBuilder;

public class DeleteMenuItemImpl implements DeleteMenuItemUsecase {

    private final MenuItemRepository repository;

    public DeleteMenuItemImpl(MenuItemRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public void execute(DeleteMenuItemCommand input) throws Exception {
        this.validateDeleteMenuItemCommand(input);

        final Optional<MenuItemEntity> result = this.repository.findById(
                UUID.fromString(input.id())
        );
        if (result.isEmpty()) {
            throw new BadRequestException(ErrorMessages.notFoundEntityById(
                    "item do menu"
            ));
        }

        final MenuItemEntity entity = result.get();
        this.repository.delete(entity);
    }

    private void validateDeleteMenuItemCommand(
            DeleteMenuItemCommand data
    ) throws Exception {
        final var errorsContainer = new ErrorsContainer();
        final Set<Validation<DeleteMenuItemCommand>> validations = new HashSet<>();

        validations.add(
                new ValidationFieldBuilder<DeleteMenuItemCommand>("id")
                        .required()
                        .uuid()
                        .build()
        );

        final Validation<DeleteMenuItemCommand> validator = new ValidationComposite<>(
                validations,
                errorsContainer
        );
        validator.validate(data);
    }
}
