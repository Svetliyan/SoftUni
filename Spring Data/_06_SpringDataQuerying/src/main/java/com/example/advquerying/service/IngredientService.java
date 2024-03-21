package com.example.advquerying.service;

import java.util.List;

public interface IngredientService {
    List<String> getAllIngredientWithStartingName(String symbol);
    int deleteIngredientByName(String name);
    int updatedIngredientPrices();
    int updatedIngredientPricesForGivenNames();
}
