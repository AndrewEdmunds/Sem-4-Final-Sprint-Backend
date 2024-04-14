package com.keyin.finalsprint;

import com.keyin.finalsprint.Entity.LogEntity;
import com.keyin.finalsprint.Entity.RecipeEntity;
import com.keyin.finalsprint.Repository.LogRepository;
import com.keyin.finalsprint.Repository.RecipeRepository;
import com.keyin.finalsprint.Service.SearchService;

import org.junit.jupiter.api.Test;



import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SearchServiceTest {

    @Autowired
    private SearchService searchService;

    @MockBean
    private RecipeRepository recipeRepository;

    @MockBean
    private LogRepository logRepository;

    @Test
    void testExactSearch() {
        String query = "Beef Str Fry";
        String database = "postgre";
        Long userId = 56L;
        List<RecipeEntity> expectedRecipes = new ArrayList<>();
        expectedRecipes.add(new RecipeEntity("Beef Stir Fry", "nuts", "Grease a baking sheet and preheat the oven to 400°F. Roll out the dough and cut into desired shapes. Place the shapes on the baking sheet and bake for 10-12 minutes or until lightly golden.", "15 minutes", "5 minutes", "20 minutes", "9", "http://dummyimage.com/188x100.png/ff4444/ffffff", "postgres"));
        when(recipeRepository.findByExactSearch(query)).thenReturn(expectedRecipes);

        List<RecipeEntity> actualRecipes = searchService.search(query, database, "exact", userId);

        assertEquals(expectedRecipes, actualRecipes);
        verify(logRepository, times(1)).save(any(LogEntity.class));
    }

    @Test
    void testKeywordSearch() {
        String query = "Beef";
        String database = "postgre";
        Long userId = 56L;
        List<RecipeEntity> expectedRecipes = new ArrayList<>();
        expectedRecipes.add(new RecipeEntity("Beef Stir Fry", "nuts", "Grease a baking sheet and preheat the oven to 400°F. Roll out the dough and cut into desired shapes. Place the shapes on the baking sheet and bake for 10-12 minutes or until lightly golden.", "15 minutes", "5 minutes", "20 minutes", "9", "http://dummyimage.com/188x100.png/ff4444/ffffff", "postgres"));

        when(recipeRepository.findByKeywordSearch(query)).thenReturn(expectedRecipes);

        List<RecipeEntity> actualRecipes = searchService.search(query, database, "keyword", userId);

        assertEquals(expectedRecipes, actualRecipes);
        System.out.println(expectedRecipes);
        System.out.println(actualRecipes);
        verify(logRepository, times(1)).save(any(LogEntity.class));
    }
}
