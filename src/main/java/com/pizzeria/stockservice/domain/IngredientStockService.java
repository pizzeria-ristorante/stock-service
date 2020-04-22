package com.pizzeria.stockservice.domain;

import com.pizzeria.stockservice.application.IngredientDto;
import com.pizzeria.stockservice.infrastructure.IngredientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class IngredientStockService {

    private final IngredientRepository ingredientRepository;

    public Optional<Ingredient> getIngredientById(int id) {
        return ingredientRepository.findById(id);
    }

    public Iterable<Ingredient> getAllIngredientsInStock() {
        return ingredientRepository.findAll();
    }

    public void removeIngredientFromStock(IngredientDto ingredientDto) {
        List<Ingredient> foundIngredients = ingredientRepository.findByName(ingredientDto.getName());
        if(foundIngredients.size() != 1) {
            log.error("Unknown ingredient: " + ingredientDto.getName());
            throw new IllegalArgumentException("Unknown ingredient: " + ingredientDto.getName());
        }
        Ingredient ingredient = foundIngredients.get(0);
        ingredient.setQuantity(ingredient.getQuantity() - ingredientDto.getQuantity());
        if(ingredient.getQuantity() < 0) {
            log.error("Not enough of " + ingredientDto.getName() + " in stock!");
            throw new IllegalArgumentException("Not enough of " + ingredientDto.getName() + " in stock!");
        }
        ingredientRepository.save(ingredient);
    }
}
