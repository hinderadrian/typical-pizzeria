package com.project.pizza.service;

import com.project.pizza.persistence.entity.PizzaEntity;
import com.project.pizza.persistence.repository.PizzaPagSortRepository;
import com.project.pizza.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository pizzaPagSortRepository;



    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

    public Page<PizzaEntity> getAllPizzas(int page, int elements) {
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.pizzaPagSortRepository.findAll(pageRequest);
    }

    public Page<PizzaEntity> getAvailable(int page, int elements, String sortBy, String sortDirection) {
        Sort sortRequest = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sortRequest);

        int count = this.pizzaRepository.countAllByVeganTrue();
        System.out.println(count);
        //return this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
        return this.pizzaPagSortRepository.findByAvailableTrue(pageRequest);
    }

    public PizzaEntity getPizzaById(int pizzaId) {
        return this.pizzaRepository.findById(pizzaId).orElse(null);
    }

    public PizzaEntity savePizza(PizzaEntity pizza) {
        return this.pizzaRepository.save(pizza);
    }

    public boolean exists(Integer pizzaId) {
        return this.pizzaRepository.existsById(pizzaId);
    }

    public void deletePizzaById(int pizzaId) {
        this.pizzaRepository.deleteById(pizzaId);
    }

    public PizzaEntity getByName(String pizzaName) {
        return this.pizzaRepository.findFirstAllByAvailableTrueAndNameIgnoreCase(pizzaName)
                .orElseThrow(() -> new RuntimeException("Pizza not found"));
    }

    public List<PizzaEntity> getWith(String ingredient) {
        return this.pizzaRepository.searchAllByAvailableTrueAndDescriptionContainsIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getWithout(String ingredient) {
        return this.pizzaRepository.searchAllByAvailableTrueAndDescriptionNotContainsIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getCheapest(double price) {
        return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }
}
