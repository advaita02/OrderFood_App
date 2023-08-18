package com.example.foodapp;

import com.example.foodapp.Database.Entity.Food;

import java.util.List;

public class ListContentFood {
    private String name_food;

    private List<Food> foods;

    public ListContentFood(String name_food, List<Food> foods) {
        this.name_food = name_food;
        this.foods = foods;
    }


    public String getName_food() {
        return name_food;
    }

    public void setName_food(String name_food) {
        this.name_food = name_food;
    }


    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
}
