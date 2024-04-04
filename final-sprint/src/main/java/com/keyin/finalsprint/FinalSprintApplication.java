package com.keyin.finalsprint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.keyin.finalsprint.entities.Recipe;
import com.keyin.finalsprint.Repos.RecipeRepo;
import java.util.List;
@SpringBootApplication
public class FinalSprintApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(FinalSprintApplication.class, args);

        // Retrieve RecipeRepo bean from application context
        RecipeRepo recipeRepo = context.getBean(RecipeRepo.class);

        // Example: Find recipes containing a specific title
        List<Recipe> recipes = recipeRepo.findByTitleContainingIgnoreCase("Beef Stir Fry");

        // Print retrieved recipes
        System.out.println("Recipes:");
        for (Recipe recipe : recipes) {
            System.out.println(recipe);
        }
    }
}
