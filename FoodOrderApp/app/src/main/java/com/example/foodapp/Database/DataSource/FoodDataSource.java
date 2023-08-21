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
            MySQLiteHelper.COLUMN_IMG_FOOD,
            MySQLiteHelper.COLUMN_CATEGORY
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

    public long insertFood(String name, int price, String description, int size, byte[] image, int categoryId) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_NAME_FOOD, name);
        values.put(MySQLiteHelper.COLUMN_PRICE, price);
        values.put(MySQLiteHelper.COLUMN_DESCRIBE, description);
        values.put(MySQLiteHelper.COLUMN_SIZE, size);
        values.put(MySQLiteHelper.COLUMN_IMG_FOOD, image);
        values.put(MySQLiteHelper.COLUMN_CATEGORY, categoryId);

        long insertedId = database.insert(MySQLiteHelper.TABLE_FOOD, null, values);

        return insertedId;
    }

    public int updateFood(long foodId, String name, int price, String description, int size, byte[] image, int categoryId) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_NAME_FOOD, name);
        values.put(MySQLiteHelper.COLUMN_PRICE, price);
        values.put(MySQLiteHelper.COLUMN_DESCRIBE, description);
        values.put(MySQLiteHelper.COLUMN_SIZE, size);
        values.put(MySQLiteHelper.COLUMN_IMG_FOOD, image);
        values.put(MySQLiteHelper.COLUMN_CATEGORY, categoryId);

        return database.update(MySQLiteHelper.TABLE_FOOD, values,
                MySQLiteHelper.COLUMN_ID_FOOD + " = ?", new String[]{String.valueOf(foodId)});
    }
    public void deleteFood(Food food) {
         long id = food.getId();
         System.out.println("Comment deleted with id: " + id);
         database.delete(MySQLiteHelper.TABLE_FOOD, MySQLiteHelper.COLUMN_ID_FOOD
                 + " = " + id, null);
     }
     public Cursor getAllFoods() {
        return database.query(MySQLiteHelper.TABLE_FOOD, null, null,
                null, null, null, null);
    }
}
