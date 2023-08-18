package com.example.foodapp.Database.Entity;

import androidx.annotation.NonNull;

public class Food {
    private int id;
    private String name;
    private int price;
    private String describe;
    private int size;
    private byte[] imgFood;
    private Category category;

    @NonNull
    @Override
    public String toString() {
        return name;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public byte[] getImgFood() {
        return imgFood;
    }

    public void setImgFood(byte[] imgFood) {
        this.imgFood = imgFood;
    }
}
