package com.keyin.finalsprint.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.keyin.finalsprint.Entity.RecipeEntity;

import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<RecipeEntity, Long> {
    public List<RecipeEntity> findAll();

    // Keyword search method
    @Query("SELECT r FROM RecipeEntity r " +
    "WHERE LOWER(r.title) LIKE %:query% OR LOWER(r.ingredients) LIKE %:query% OR LOWER(r.instructions) LIKE %:query% " +
    "ORDER BY CASE " +
    "WHEN LOWER(r.title) LIKE %:query% THEN 0 " +
    "WHEN LOWER(r.ingredients) LIKE %:query% THEN 1 " +
    "WHEN LOWER(r.instructions) LIKE %:query% THEN 2 " +
    "ELSE 3 END")
    List<RecipeEntity> findByKeywordSearch(@Param("query") String query);


    // Exact search method
    @Query("SELECT r FROM RecipeEntity r WHERE LOWER(r.title) = LOWER(:query) OR LOWER(r.ingredients) = LOWER(:query) OR LOWER(r.instructions) = LOWER(:query)")
    List<RecipeEntity> findByExactSearch(String query);
}

