package com.project.pizza.service;

import com.project.pizza.persistence.entity.PizzaEntity;
import com.project.pizza.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<PizzaEntity> getAllPizzas() {
        return this.pizzaRepository.findAll();
    }

    public List<PizzaEntity> getAvailable() {
        return this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
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
        return this.pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(pizzaName);
    }

    public List<PizzaEntity> getWith(String ingredient) {
        return this.pizzaRepository.searchAllByAvailableTrueAndDescriptionContainsIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getWithout(String ingredient) {
        return this.pizzaRepository.searchAllByAvailableTrueAndDescriptionNotContainsIgnoreCase(ingredient);
    }
}
