package com.keyin.finalsprint.Recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/search")
    public List<RecipeEntity> searchRecipes(
            @RequestParam String query,
            @RequestParam String database,
            @RequestParam String searchType,
            @RequestParam String userId
    ) {
        recipeService.logQuery(userId, query);
        return recipeService.searchRecipes(query, database, searchType);
    }
}