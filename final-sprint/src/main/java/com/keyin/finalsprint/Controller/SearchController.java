package com.keyin.finalsprint.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;


import com.keyin.finalsprint.Service.SearchService;
import com.keyin.finalsprint.Entity.RecipeEntity;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;


@RestController
public class SearchController {

    private final Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private SearchService searchService;

    @GetMapping("/api/search")
    public ResponseEntity<List<RecipeEntity>> searchRecipes(
            @RequestParam String query,
            @RequestParam String database,
            @RequestParam String searchType,
            @RequestParam Long userId) {

        try {
            logger.info("Received search request for query '{}' from user '{}'", query, userId);

            List<RecipeEntity> recipes = searchService.search(query, database, searchType, userId);

            return ResponseEntity.ok().body(recipes);
        } catch (Exception e) {
            logger.error("Error executing search query:", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
