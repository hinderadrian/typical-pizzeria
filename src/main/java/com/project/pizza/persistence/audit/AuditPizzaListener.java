package com.project.pizza.persistence.audit;

import com.project.pizza.persistence.entity.PizzaEntity;
import jakarta.persistence.*;
import org.springframework.util.SerializationUtils;

public class AuditPizzaListener {

    private PizzaEntity currentPizza;

    @PostLoad
    public void postLoad(PizzaEntity pizzaEntity) {
        System.out.println("POST LOAD");
        this.currentPizza = SerializationUtils.clone(pizzaEntity);
    }

    @PreUpdate
    public void onPreUpdate(PizzaEntity pizzaEntity) {
        System.out.println("PRE UPDATE");
        this.currentPizza = SerializationUtils.clone(pizzaEntity);
    }

    @PostPersist
    @PostUpdate
    public void onPostPersist(PizzaEntity pizzaEntity) {
        System.out.println("POST PERSIST OR UPDATE");
        System.out.println("OLD VALUE: " + this.currentPizza.toString());
        System.out.println("NEW VALUE: " + pizzaEntity.toString());
    }

    @PreRemove
    public void onPreDelete(PizzaEntity pizzaEntity) {
        System.out.println(pizzaEntity.toString());
    }
}
