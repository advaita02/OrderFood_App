package com.example.foodapp.Database.DataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.foodapp.Database.Entity.Category;
import com.example.foodapp.Database.Entity.Food;
import com.example.foodapp.Database.MySQLiteHelper;

import java.util.ArrayList;
import java.util.List;


public class FoodDataSource {
    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID_FOOD,
            MySQLiteHelper.COLUMN_NAME_FOOD,
            MySQLiteHelper.COLUMN_PRICE,
            MySQLiteHelper.COLUMN_DESCRIBE,
            MySQLiteHelper.COLUMN_SIZE,
            MySQLiteHelper.COLUMN_CATEGORY,
            MySQLiteHelper.COLUMN_IMG_FOOD
    };

    public FoodDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Food insertFood(String name, int price, String describe, int size, Category category) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_NAME_FOOD, name);
        values.put(MySQLiteHelper.COLUMN_PRICE, price);
        values.put(MySQLiteHelper.COLUMN_DESCRIBE, describe);
        values.put(MySQLiteHelper.COLUMN_SIZE, size);
        values.put(MySQLiteHelper.COLUMN_CATEGORY, category.getId());

    // add data to Food table
        long insertId = database.insert(MySQLiteHelper.TABLE_FOOD, null, values);

    // Get and return data
        Cursor cursor = database.query(MySQLiteHelper.TABLE_FOOD,
                allColumns, MySQLiteHelper.COLUMN_ID_FOOD + " = " + insertId, null
                ,null, null, null);
        cursor.moveToFirst();
        Food newFood = cursorToFood(cursor);
        cursor.close();
        return newFood;
    }
    public void deleteFood(Food food) {
         long id = food.getId();
         System.out.println("Comment deleted with id: " + id);
         database.delete(MySQLiteHelper.TABLE_FOOD, MySQLiteHelper.COLUMN_ID_FOOD
                 + " = " + id, null);
     }
     public List<Food> getAllFoods() {
        List<Food> foods = new ArrayList<Food>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_FOOD, allColumns, null,
                null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Food food = cursorToFood(cursor);
            foods.add(food);
            cursor.moveToNext();
        }
        cursor.close();
        return foods;
    }
    private Food cursorToFood(Cursor cursor) {
        Food food = new Food();
        food.setId(cursor.getInt(0));
        food.setName(cursor.getString(1));
        food.setPrice(cursor.getInt(2));
        food.setDescribe(cursor.getString(3));
        food.setSize(cursor.getInt(4));
        food.getCategory().setId(cursor.getInt(5));
        return food;
    }
}
