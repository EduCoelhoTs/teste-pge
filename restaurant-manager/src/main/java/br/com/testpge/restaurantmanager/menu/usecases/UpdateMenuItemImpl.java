package br.com.testpge.restaurantmanager.menu.usecases;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import br.com.testpge.restaurantmanager.menu.entities.MenuItemEntity;
import br.com.testpge.restaurantmanager.menu.enums.MenuItemType;
import br.com.testpge.restaurantmanager.menu.repositories.MenuItemRepository;
import br.com.testpge.restaurantmanager.menu.usecases.dtos.UpdateMenuItemCommand;
import br.com.testpge.restaurantmanager.shared.constants.ErrorMessages;
import br.com.testpge.restaurantmanager.shared.exceptions.BadRequestException;
import br.com.testpge.restaurantmanager.shared.exceptions.ErrorsContainer;
import br.com.testpge.restaurantmanager.shared.validations.Validation;
import br.com.testpge.restaurantmanager.shared.validations.ValidationComposite;
import br.com.testpge.restaurantmanager.shared.validations.ValidationFieldBuilder;

public class UpdateMenuItemImpl implements UpdateMenuItemUsecase {

    private final MenuItemRepository repository;

    public UpdateMenuItemImpl(MenuItemRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public void execute(UpdateMenuItemCommand data) throws Exception {
        this.validateUpdateMenuItemCommand(data);

        final Optional<MenuItemEntity> result = this.repository.findById(
                UUID.fromString(data.id())
        );
        if (result.isEmpty()) {
            throw new BadRequestException(ErrorMessages.notFoundEntityById(
                    "item do menu"
            ));
        }

        final MenuItemEntity entity = result.get();
        entity.setName(data.name());
        entity.setPrice(data.price());
        entity.setDescription(data.description());
        entity.setType(data.type());
        this.repository.save(entity);
    }

    private void validateUpdateMenuItemCommand(
            UpdateMenuItemCommand data
    ) throws Exception {
        final var errorsContainer = new ErrorsContainer();
        final Set<Validation<UpdateMenuItemCommand>> validations = new HashSet<>();

        validations.add(
                new ValidationFieldBuilder<UpdateMenuItemCommand>("id")
                        .required()
                        .uuid()
                        .build()
        );
        validations.add(
                new ValidationFieldBuilder<UpdateMenuItemCommand>("name")
                        .required()
                        .minLength(2)
                        .maxLength(255)
                        .build()
        );
        validations.add(
                new ValidationFieldBuilder<UpdateMenuItemCommand>("description")
                        .required()
                        .minLength(2)
                        .maxLength(255)
                        .build()
        );
        validations.add(
                new ValidationFieldBuilder<UpdateMenuItemCommand>("price")
                        .required()
                        .minValue(0.01)
                        .maxValue(10000000.0)
                        .build()
        );
        validations.add(
                new ValidationFieldBuilder<UpdateMenuItemCommand>("type")
                        .required()
                        .enumToCompare(MenuItemType.class)
                        .build()
        );

        final Validation<UpdateMenuItemCommand> validator = new ValidationComposite<>(
                validations,
                errorsContainer
        );
        validator.validate(data);
    }

}
