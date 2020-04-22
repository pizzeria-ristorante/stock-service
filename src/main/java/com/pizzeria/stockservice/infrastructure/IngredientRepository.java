package com.pizzeria.stockservice.infrastructure;

import com.pizzeria.stockservice.domain.Ingredient;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {

    @Query("Select * from Ingredient u where u.name = :name")
    List<Ingredient> findByName(String name);
}
