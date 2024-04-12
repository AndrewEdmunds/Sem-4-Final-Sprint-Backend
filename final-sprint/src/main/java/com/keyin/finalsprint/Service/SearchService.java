package com.keyin.finalsprint.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keyin.finalsprint.Repository.RecipeRepository;
import com.keyin.finalsprint.Entity.RecipeEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
public class SearchService {

    private final Logger logger = LoggerFactory.getLogger(SearchService.class);

    @Autowired
    private RecipeRepository recipeRepository;

    public List<RecipeEntity> search(String query, String database, String searchType, Long userId) {
        try {
            logger.info("Received search request for query '{}' from user '{}'", query, userId);

            List<RecipeEntity> recipes = null;

            // Search in PostgreSQL database
            if ("postgre".equals(database) || "both".equals(database)) {
                if ("exact".equals(searchType)) {
                    recipes = recipeRepository.findByExactSearch(query);
                } else if ("keyword".equals(searchType)) {
                    recipes = recipeRepository.findByKeywordSearch(query);
                }
            }
            

            return recipes;
        } catch (Exception e) {
            logger.error("Error executing search query:", e);
            throw new RuntimeException("An error occurred while searching.");
        }
    }
}

