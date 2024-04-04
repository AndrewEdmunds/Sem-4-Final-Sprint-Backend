package com.keyin.finalsprint.entities;
import javax.persistence.*;

@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "ingredients")
    private String ingredients;

    @Column(name = "instructions")
    private String instructions;

    @Column(name = "prep_time")
    private int prepTime;

    @Column(name = "cook_time")
    private int cookTime;

    @Column(name = "total_time")
    private int totalTime;

    @Column(name = "servings")
    private int servings;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "database")
    private String database;

      // Constructors
      public Recipe() {
    }

    public Recipe(String title, String ingredients, String instructions, int prepTime, int cookTime, int totalTime, int servings, String imageUrl, String database) {
        this.title = title;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.totalTime = totalTime;
        this.servings = servings;
        this.imageUrl = imageUrl;
        this.database = database;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public int getCookTime() {
        return cookTime;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public int getServings() {
        return servings;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDatabase() {
        return database;
    }
}