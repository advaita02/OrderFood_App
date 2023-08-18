package com.example.foodapp.Database.Entity;

public class Category {
    private int id;
    private String name;

//    public Category(int id, String categoryName) {
//    }

    public Category() {

    }
    public Category(int id, String name_cate) {
        this.id = id;
        this.name = name_cate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//    public String getCategoryName() {
//        return name;
//    }
}
