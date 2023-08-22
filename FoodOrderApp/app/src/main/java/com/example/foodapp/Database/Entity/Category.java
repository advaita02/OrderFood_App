package com.example.foodapp.Database.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Category implements Parcelable {
    private int id;
    private String name;
    private byte[] img_cate;

    public Category() {}
    public Category(String name, byte[] img_cate) {
        this.name = name;
        this.img_cate = img_cate;
    }

    public Category(int id, String categoryName) {
    }


    public Category(int id, String name_cate, byte[] img_cate) {
        this.id = id;
        this.name = name_cate;
        this.img_cate = img_cate;
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


    public byte[] getImg_cate() {
        return img_cate;
    }

    public void setImg_cate(byte[] img_cate) {
        this.img_cate = img_cate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {

    }
}
