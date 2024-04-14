package com.keyin.finalsprint.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.keyin.finalsprint.Service.SearchService;
import com.keyin.finalsprint.Entity.RecipeEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller
public class SearchController {

    private final Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private SearchService searchService;

    @GetMapping("/api/search")
    @ResponseBody
    public List<RecipeEntity> searchRecipes(
            @RequestParam String query,
            @RequestParam String database,
            @RequestParam String searchType,
            @RequestParam Long userId) {

        try {
            logger.info("Received search request for query '{}' from user '{}'", query, userId);

            List<RecipeEntity> recipes = searchService.search(query, database, searchType, userId);

            return recipes;
        } catch (Exception e) {
            logger.error("Error executing search query:", e);
            return null;
        }
    }

    @GetMapping("/search_results")
    public String showSearchResults(
            @RequestParam String query,
            @RequestParam String database,
            @RequestParam String searchType,
            @RequestParam Long userId,
            Model model) {

        try {
            logger.info("Received search request for query '{}' from user '{}'", query, userId);

            List<RecipeEntity> recipes = searchService.search(query, database, searchType, userId);

            model.addAttribute("recipes", recipes);


            return "search_results";
        } catch (Exception e) {
            logger.error("Error executing search query:", e);
            // Handle error
            return "error"; 
        }
    }
}
