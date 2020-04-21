package com.pizzeria.stockservice.domain;

import com.pizzeria.stockservice.infrastructure.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IngredientStockService {

    private final IngredientRepository ingredientRepository;

    public Optional<Ingredient> getIngredientById(int id) {
        return ingredientRepository.findById(id);
    }

    public Iterable<Ingredient> getAllIngredientsInStock() {
        return ingredientRepository.findAll();
    }
}
