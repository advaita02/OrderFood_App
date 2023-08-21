package com.example.foodapp;

import com.example.foodapp.Database.Entity.Category;

import java.util.List;

public class ListContentCate {
    private String name_content;
    private List<Category> cates;

    public ListContentCate(String name_content, List<Category> cates) {
        this.name_content = name_content;
        this.cates = cates;
    }


    public String getName_content() {
        return name_content;
    }

    public void setName_content(String name_content) {
        this.name_content = name_content;
    }

    public List<Category> getCates() {
        return cates;
    }

    public void setCates(List<Category> cates) {
        this.cates = cates;
    }
}
