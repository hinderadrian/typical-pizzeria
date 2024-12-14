package com.project.pizza.web.controller;

import com.project.pizza.persistence.entity.PizzaEntity;
import com.project.pizza.service.PizzaService;
import com.project.pizza.service.dto.UpdatePizzaPriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping("")
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "8") int elements) {
        return ResponseEntity.ok(this.pizzaService.getAllPizzas(page, elements));
    }

    @GetMapping("/available")
    public ResponseEntity<Page<PizzaEntity>> getAvailable(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "8") int elements,
                                                          @RequestParam(defaultValue = "price") String sortBy, Sort sort,
                                                          @RequestParam(defaultValue = "ASC") String sortDirection) {
        return ResponseEntity.ok(this.pizzaService.getAvailable(page, elements, sortBy, sortDirection));
    }

    @GetMapping("/with/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getWith(@PathVariable String ingredient) {
        return ResponseEntity.ok(this.pizzaService.getWith(ingredient));
    }

    @GetMapping("/without/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getWithout(@PathVariable String ingredient) {
        return ResponseEntity.ok(this.pizzaService.getWithout(ingredient));
    }

    @GetMapping("/pizza/{pizzaId}")
    public ResponseEntity<PizzaEntity> getPizza(@PathVariable Integer pizzaId) {
        return ResponseEntity.ok(pizzaService.getPizzaById(pizzaId));
    }

    @GetMapping("/pizza/name/{pizzaName}")
    public ResponseEntity<PizzaEntity> getByName(@PathVariable String pizzaName) {
        return ResponseEntity.ok(this.pizzaService.getByName(pizzaName));
    }

    @GetMapping("/cheapest/{price}")
    public ResponseEntity<List<PizzaEntity>> getCheapest(@PathVariable double price) {
        return ResponseEntity.ok(this.pizzaService.getCheapest(price));
    }

    @PostMapping("/pizza")
    public ResponseEntity<PizzaEntity> createPizza(@RequestBody PizzaEntity pizza) {
        if(pizza.getPizzaId() == null || !this.pizzaService.exists(pizza.getPizzaId())) {
            return ResponseEntity.ok(pizzaService.savePizza(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/pizza")
    public ResponseEntity<PizzaEntity> updatePizza(@RequestBody PizzaEntity pizza) {
        if(pizza.getPizzaId() != null && this.pizzaService.exists(pizza.getPizzaId())) {
            return ResponseEntity.ok(pizzaService.savePizza(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/pizza/price")
    public ResponseEntity<PizzaEntity> updatePizzaPrice(@RequestBody UpdatePizzaPriceDto pizzaPriceDto) {
        if(this.pizzaService.exists(pizzaPriceDto.getPizzaId())) {
            this.pizzaService.updatePizzaPrice(pizzaPriceDto);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/pizza/{pizzaId}")
    public ResponseEntity<Void> deletePizza(@PathVariable Integer pizzaId) {
        if (this.pizzaService.exists(pizzaId)) {
            pizzaService.deletePizzaById(pizzaId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
