package com.keyin.finalsprint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.keyin.finalsprint.Recipe.RecipeEntity;
import com.keyin.finalsprint.Recipe.RecipeRepository;
import java.util.List;

@SpringBootApplication
public class FinalSprintApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(FinalSprintApplication.class, args);

        // Retrieve RecipeRepository bean from application context
        RecipeRepository recipeRepo = context.getBean(RecipeRepository.class);

        // Example: Find recipe containing a specific title
		List<RecipeEntity> recipes = recipeRepo.findByTitle("Beef Stir Fry");

        // Print retrieved recipe
		System.out.println("Recipes:");
		for (RecipeEntity recipe : recipes) {
			System.out.println(recipe);
    }
}
}
