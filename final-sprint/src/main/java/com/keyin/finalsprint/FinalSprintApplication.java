package com.keyin.finalsprint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import com.keyin.finalsprint.Recipe.RecipeEntity;
import com.keyin.finalsprint.Recipe.RecipeRepository;
import com.keyin.finalsprint.Recipe.RecipeService;
import com.keyin.finalsprint.Recipe.RecipeController;
import com.keyin.finalsprint.users.UserEntity;
import com.keyin.finalsprint.users.UserRepository;

import java.util.List;


@SpringBootApplication
public class FinalSprintApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(FinalSprintApplication.class, args);

        // Retrieve RecipeRepository bean from application context
        RecipeRepository recipeRepo = context.getBean(RecipeRepository.class);

        // Example: Find recipe containing a specific title
		List<RecipeEntity> recipes = recipeRepo.findAll();

        UserRepository userRepo = context.getBean(UserRepository.class);

        List<UserEntity> users = userRepo.findByUsername("me");

        // Print retrieved recipe
		System.out.println("Recipes:");
		for (RecipeEntity recipe : recipes) {
			System.out.println(recipe);
    }
        System.out.println("Users:");
        for (UserEntity user : users) {
            System.out.println(user);
        }
}
}


/* 
@SpringBootApplication
public class FinalSprintApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(FinalSprintApplication.class, args);

        // Retrieve RecipeRepository bean from application context
        RecipeRepository recipeRepo = context.getBean(RecipeRepository.class);

        // Example: Find recipe containing a specific title
		List<RecipeEntity> recipes = recipeRepo.findByTitle("Beef Stir Fry");

        UserRepository userRepo = context.getBean(UserRepository.class);

        List<UserEntity> users = userRepo.findByUsername("me");

        // Print retrieved recipe
		System.out.println("Recipes:");
		for (RecipeEntity recipe : recipes) {
			System.out.println(recipe);
    }
        System.out.println("Users:");
        for (UserEntity user : users) {
            System.out.println(user);
        }
}
}
*/
