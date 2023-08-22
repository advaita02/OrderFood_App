package com.example.foodapp.Database.DataSource;

import android.annotation.SuppressLint;
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
    private String[] allColumns = {MySQLiteHelper.COLUMN_ID_FOOD,
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
        open();
        return database.query(MySQLiteHelper.TABLE_FOOD, null, null,
                null, null, null, null);
    }


    public ArrayList<Food> getBestSellingFoods() {
        ArrayList<Food> bestSellingFoods = new ArrayList<>();

        String query = "SELECT " +
                MySQLiteHelper.COLUMN_FOOD_ORDER + ", " +
                "SUM(" + MySQLiteHelper.COLUMN_QUANTITY + ") as totalQuantity " +
                "FROM " + MySQLiteHelper.TABLE_ORDER_ITEM +
                " GROUP BY " + MySQLiteHelper.COLUMN_FOOD_ORDER +
                " ORDER BY totalQuantity DESC LIMIT 10";  // Lấy 10 món ăn bán chạy nhất

        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            @SuppressLint("Range") int foodID = cursor.getInt(cursor.getColumnIndex(MySQLiteHelper.COLUMN_FOOD_ORDER));
            @SuppressLint("Range") int totalQuantity = cursor.getInt(cursor.getColumnIndex("totalQuantity"));

            Food food = getFoodById(foodID);  // Hàm này cần phải có để lấy thông tin món ăn bằng ID
            if (food != null) {
                bestSellingFoods.add(food);
            }
            cursor.moveToNext();
        }

        cursor.close();
        return bestSellingFoods;
    }

    public Food getFoodById(int foodId) {
        Food food = null;

        String query = "SELECT * FROM " + MySQLiteHelper.TABLE_FOOD +
                " WHERE " + MySQLiteHelper.COLUMN_ID_FOOD + " = ?";

        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(foodId)});
        if (cursor.moveToFirst()) {
            food = cursorToFood(cursor);
        }

        cursor.close();
        return food;
    }

    private Food cursorToFood(Cursor cursor) {
        int id = cursor.getInt(0);
        String nameFood = cursor.getString(1);
        int price = cursor.getInt(2);
        String describe = cursor.getString(3);
        int size = cursor.getInt(4);
        byte[] img = cursor.getBlob(5);

        Category category = new Category();
        category.setId(cursor.getInt(6));

        return new Food(id, nameFood, describe, price, size, img, category);
    }


    public int getCountOfIds() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {"COUNT(id_food)"}; // Thay "id_column_name" bằng tên cột Id
        Cursor cursor = db.query(MySQLiteHelper.TABLE_FOOD, projection, null, null, null, null, null);

        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }

        cursor.close();
        return count;
    }
}
