package br.com.testpge.restaurantmanager.shared.data;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProcessedEventDataGateway
        extends JpaRepository<ProcessedEventEntity, UUID> {

}
