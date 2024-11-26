package br.com.testpge.restaurantmanager.menu.usecases;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import br.com.testpge.restaurantmanager.menu.entities.MenuItemEntity;
import br.com.testpge.restaurantmanager.menu.enums.MenuItemType;
import br.com.testpge.restaurantmanager.menu.repositories.MenuItemRepository;
import br.com.testpge.restaurantmanager.menu.usecases.dtos.CreateMenuItemCommand;
import br.com.testpge.restaurantmanager.shared.data.dtos.EntityIdOutput;
import br.com.testpge.restaurantmanager.shared.exceptions.ErrorsContainer;
import br.com.testpge.restaurantmanager.shared.validations.Validation;
import br.com.testpge.restaurantmanager.shared.validations.ValidationComposite;
import br.com.testpge.restaurantmanager.shared.validations.ValidationFieldBuilder;

public class CreateMenuItemImpl implements CreateMenuItemUsecase {

    private final MenuItemRepository repository;

    public CreateMenuItemImpl(MenuItemRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public EntityIdOutput execute(CreateMenuItemCommand data) throws Exception {
        this.validateCreateMenuItemCommand(data);

        final var entity = new MenuItemEntity();
        entity.setId(UUID.randomUUID());
        entity.setName(data.name());
        entity.setPrice(data.price());
        entity.setDescription(data.description());
        entity.setType(data.type());
        this.repository.save(entity);

        return new EntityIdOutput(entity.getId().toString());
    }

    private void validateCreateMenuItemCommand(
            CreateMenuItemCommand data
    ) throws Exception {
        final var errorsContainer = new ErrorsContainer();
        final Set<Validation<CreateMenuItemCommand>> validations = new HashSet<>();

        validations.add(
                new ValidationFieldBuilder<CreateMenuItemCommand>("name")
                        .required()
                        .minLength(2)
                        .maxLength(255)
                        .build()
        );
        validations.add(
                new ValidationFieldBuilder<CreateMenuItemCommand>("description")
                        .required()
                        .minLength(2)
                        .maxLength(255)
                        .build()
        );
        validations.add(
                new ValidationFieldBuilder<CreateMenuItemCommand>("price")
                        .required()
                        .minValue(0.01)
                        .maxValue(10000000.0)
                        .build()
        );
        validations.add(
                new ValidationFieldBuilder<CreateMenuItemCommand>("type")
                        .required()
                        .enumToCompare(MenuItemType.class)
                        .build()
        );

        final Validation<CreateMenuItemCommand> validator = new ValidationComposite<>(
                validations,
                errorsContainer
        );
        validator.validate(data);
    }

}
