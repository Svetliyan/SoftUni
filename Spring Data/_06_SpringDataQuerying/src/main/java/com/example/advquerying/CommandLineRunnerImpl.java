package com.example.advquerying;

import com.example.advquerying.service.IngredientService;
import com.example.advquerying.service.ShampooService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final ShampooService shampooService;
    private final IngredientService ingredientService;

    public CommandLineRunnerImpl(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        exercise 01 "Select Shampoos by Size".
//        this.shampooService.getAllShampoosByGivenSize(reader.readLine())
//                 .forEach(System.out::println);

//        exercise 07 "Select Shampoos by Ingredients".
//        this.shampooService.getAllShampoosContainingIngredient(List.of(reader.readLine().split(" ")))
//                .forEach(System.out::println);

//        exercise 02 "Select Shampoos by Size or Label".
//        this.shampooService.getAllShampoosByGivenSizeOrLabel("medium", 10)
//                .forEach(System.out::println);

//        exercise 03 "Select Shampoos by Price".
//        this.shampooService.getAllShampoosWithPriceHigherThan(BigDecimal.valueOf(5))
//                .forEach(System.out::println);

//        exercise 04 "Select Ingredients by Name".
//        this.ingredientService.getAllIngredientWithStartingName("M")
//                .forEach(System.out::println);

//        exercise 06 "Count Shampoos by Price".
//        System.out.println(this.shampooService.getAllShampoosWithPriceLowerThan(BigDecimal.valueOf(8.5)));

//        exercise 08 "Select Shampoos by Ingredients Count".
//        System.out.println(this.shampooService.getAllShampoosWithCountOfIngredientsBelowNumber());
//
//        exercise 09 "Delete Ingredients by Name".
//        System.out.println(this.ingredientService.deleteIngredientByName("Nettle"));
//
//        exercise 10 "Update Ingredients by Price".
//        this.ingredientService.updatedIngredientPrices();
//
//        exercise 11 "Update Ingredients by Names".
//        this.ingredientService.updatedIngredientPricesForGivenNames();
    }
}
