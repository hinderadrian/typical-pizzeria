package com.project.pizza.persistence.repository;

import com.project.pizza.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {

    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();

    Optional<PizzaEntity> findFirstAllByAvailableTrueAndNameIgnoreCase(String name);

    List<PizzaEntity> searchAllByAvailableTrueAndDescriptionContainsIgnoreCase(String description);

    List<PizzaEntity> searchAllByAvailableTrueAndDescriptionNotContainsIgnoreCase(String description);

    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(double price);

    int countAllByVeganTrue();
}
