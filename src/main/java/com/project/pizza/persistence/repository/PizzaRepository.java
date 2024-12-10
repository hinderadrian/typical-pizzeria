package com.project.pizza.persistence.repository;

import com.project.pizza.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {

    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();

    PizzaEntity findAllByAvailableTrueAndNameIgnoreCase(String name);

    List<PizzaEntity> searchAllByAvailableTrueAndDescriptionContainsIgnoreCase(String description);

    List<PizzaEntity> searchAllByAvailableTrueAndDescriptionNotContainsIgnoreCase(String description);
}
