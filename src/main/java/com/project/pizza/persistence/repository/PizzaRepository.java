package com.project.pizza.persistence.repository;

import com.project.pizza.persistence.entity.PizzaEntity;
import com.project.pizza.service.dto.UpdatePizzaPriceDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {

    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();

    Optional<PizzaEntity> findFirstAllByAvailableTrueAndNameIgnoreCase(String name);

    List<PizzaEntity> searchAllByAvailableTrueAndDescriptionContainsIgnoreCase(String description);

    List<PizzaEntity> searchAllByAvailableTrueAndDescriptionNotContainsIgnoreCase(String description);

    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(double price);

    int countAllByVeganTrue();

    @Query(nativeQuery = true, value = "UPDATE pizza SET price = :price WHERE id_pizza = :id")
    void updatePrice(@Param("id") int id, @Param("price") double price);

    //Using Spring Expression Language
    @Query(nativeQuery = true, value = """
            UPDATE pizza
            SET price = :#{ #newPizzaPrice.newPrice}
            WHERE id_pizza = :#{ #newPizzaPrice.pizzaId}""")
    @Modifying
    void updatePriceV2(@Param("newPizzaPrice") UpdatePizzaPriceDto newPizzaPrice);
}
