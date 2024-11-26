package br.com.testpge.order.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.testpge.order.entities.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
}
