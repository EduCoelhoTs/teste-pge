package br.com.testpge.restaurantmanager.menu.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.testpge.restaurantmanager.menu.entities.MenuItemEntity;
import br.com.testpge.restaurantmanager.menu.enums.MenuItemType;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItemEntity, UUID> {

    List<MenuItemEntity> findByType(MenuItemType type);
}
