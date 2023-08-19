package com.example.foodapp.Database.Entity;

public class Category {
    private int id;
    private String name;
    private byte[] img_cate;

    public Category() {}
    public Category(String name, byte[] img_cate) {
        this.name = name;
        this.img_cate = img_cate;
    }

//    public Category(int id, String categoryName) {
//    }


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

    public byte[] getImg_cate() {
        return img_cate;
    }

    public void setImg_cate(byte[] img_cate) {
        this.img_cate = img_cate;
    }
}
