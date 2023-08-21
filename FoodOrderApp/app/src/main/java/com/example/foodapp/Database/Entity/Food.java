package com.example.foodapp.Database.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Food implements Parcelable {
    private int id;
    private String name;
    private int price;
    private String describe;
    private int size;
    private byte[] imgFood;
    private Category category;

    private  int quantity;

    public Food(int id, String name_food, String des_food, int price_food, int size, byte[] imgFood, Category category) {
        this.id = id;
        this.name = name_food;
        this.describe = des_food;
        this.price = price_food;
        this.size = size;
        this.imgFood = imgFood;
        this.category = category;
    }

    public Food(String name_food, String des_food, int price_food) {
        this.name = name_food;
        this.describe = des_food;
        this.price = price_food;
    }

    public Food(String name_food, int price_food,int quantity) {
        this.name = name_food;
        this.price = price_food;

        this.setQuantity(quantity);
    }

    public Food() {

    }

    protected Food(Parcel in) {
        id = in.readInt();
        name = in.readString();
        price = in.readInt();
        describe = in.readString();
        size = in.readInt();
        imgFood = in.createByteArray();
        category = in.readParcelable(Category.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(price);
        dest.writeString(describe);
        dest.writeInt(size);
        dest.writeByteArray(imgFood);
        dest.writeParcelable(category, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
