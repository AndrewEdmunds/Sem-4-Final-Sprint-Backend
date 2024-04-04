package com.keyin.finalsprint.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.keyin.finalsprint.entities.Recipe;
import java.util.List;

public interface RecipeRepo extends JpaRepository<Recipe, Long> {
    List<Recipe> findByTitleContainingIgnoreCase(String title);
}
