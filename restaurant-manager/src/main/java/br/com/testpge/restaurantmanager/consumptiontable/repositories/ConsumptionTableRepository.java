package br.com.testpge.restaurantmanager.consumptiontable.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.testpge.restaurantmanager.consumptiontable.entities.ConsumptionTableEntity;

@Repository
public interface ConsumptionTableRepository extends JpaRepository<ConsumptionTableEntity, UUID> {
}
