package com.keyin.finalsprint;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;
import com.keyin.finalsprint.Service.*;
import com.keyin.finalsprint.Entity.*;
import com.keyin.finalsprint.Repository.*;

import static org.junit.jupiter.api.Assertions.assertEquals;





@SpringBootTest
class SearchServiceTest1 {

    @Autowired
    private SearchService searchService;

    @MockBean
    private RecipeRepository recipeRepository;

    @MockBean
    private LogRepository logRepository;

    @Test
    void testExactSearch() {
        // Mock data
        String query = "Beef Stir Fry";
        String database = "postgre";
        Long userId = 56L;
        List<RecipeEntity> expectedRecipes = new ArrayList<>();
        expectedRecipes.add(new RecipeEntity("Beef Stir Fry", "nuts", "Grease a baking sheet and preheat the oven to 400°F. Roll out the dough and cut into desired shapes. Place the shapes on the baking sheet and bake for 10-12 minutes or until lightly golden.", "15 minutes", "5 minutes", "20 minutes", "9", "http://dummyimage.com/188x100.png/ff4444/ffffff", "postgres"));

        // Mock the behavior of recipeRepository
        when(recipeRepository.findByExactSearch(query)).thenReturn(expectedRecipes);

        // Call the method under test
        List<RecipeEntity> actualRecipes = searchService.search(query, database, "exact", userId);

        // Assertions
        assertEquals(expectedRecipes, actualRecipes);
        verify(logRepository, times(1)).save(any(LogEntity.class));
    }

    @Test
    void testKeywordSearch() {
        // Mock data
        String query = "Beef Stir Fry";
        String database = "postgre";
        Long userId = 56L;
        List<RecipeEntity> expectedRecipes = new ArrayList<>();
        expectedRecipes.add(new RecipeEntity("Beef Stir Fry", "nuts", "Grease a baking sheet and preheat the oven to 400°F. Roll out the dough and cut into desired shapes. Place the shapes on the baking sheet and bake for 10-12 minutes or until lightly golden.", "15 minutes", "5 minutes", "20 minutes", "9", "http://dummyimage.com/188x100.png/ff4444/ffffff", "postgres"));

        // Mock the behavior of recipeRepository
        when(recipeRepository.findByKeywordSearch(query)).thenReturn(expectedRecipes);

        // Call the method under test
        List<RecipeEntity> actualRecipes = searchService.search(query, database, "keyword", userId);

        // Assertions
        assertEquals(expectedRecipes, actualRecipes);
        verify(logRepository, times(1)).save(any(LogEntity.class));
    }
}
