package com.keyin.finalsprint.Recipe;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<RecipeEntity, Long> {
    public List<RecipeEntity> findByTitle(String title);
}

