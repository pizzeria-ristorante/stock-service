package com.pizzeria.stockservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class Ingredient {

    @Id
    private Integer id;
    private String name;
    private int quantity;
}
