package com.pizzeria.stockservice.application;

import com.pizzeria.stockservice.domain.Ingredient;
import com.pizzeria.stockservice.domain.IngredientStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/ingredients")
public class StockServiceRestController {

    private final IngredientStockService ingredientStockService;

    @GetMapping
    public Set<IngredientDto> getAllIngredientsInStock(@RequestParam Optional<String> name) {
        Iterable<Ingredient> ingredients = ingredientStockService.getAllIngredientsInStock();
        Set<Ingredient> allIngredients = new HashSet<>();
        ingredients.forEach(allIngredients::add);
        return allIngredients
                .stream()
                .filter(ingredient -> name.isPresent() ? ingredient.getName().equals(name.get()) : true)
                .map(this::mapToDto)
                .collect(Collectors.toSet());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientDto> getbyId(@PathVariable("id") int id) {
        return ingredientStockService.getIngredientById(id)
                .map(ingredient -> ResponseEntity.ok().body(this.mapToDto(ingredient)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private IngredientDto mapToDto(Ingredient ingredient) {
        return new IngredientDto(ingredient.getName(), ingredient.getQuantity());
    }
}
