package com.hulk.store.domain.repository;

import com.hulk.store.domain.model.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovementRepository extends JpaRepository<Movement, Integer> {

    List<Movement> findAllMovementsByProductId(Integer productId);
}
