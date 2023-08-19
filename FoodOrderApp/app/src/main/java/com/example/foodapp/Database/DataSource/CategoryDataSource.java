package com.example.foodapp.Database.DataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.foodapp.Database.Entity.Category;
import com.example.foodapp.Database.Entity.Food;
import com.example.foodapp.Database.MySQLiteHelper;

public class CategoryDataSource {
    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    public CategoryDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long createCategory(byte[] image, String name) {
        open();
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_IMG_CATE, image);
        values.put(MySQLiteHelper.COLUMN_NAME_CATE, name);

        return database.insert(MySQLiteHelper.TABLE_CATEGORY, null, values);
    }

    public int updateCategory(long categoryId, byte[] image, String name) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_IMG_CATE, image);
        values.put(MySQLiteHelper.COLUMN_NAME_CATE, name);

        return database.update(MySQLiteHelper.TABLE_CATEGORY, values,
                MySQLiteHelper.COLUMN_ID_CATE + " = ?", new String[]{String.valueOf(categoryId)});
    }

    public int deleteCategory(long categoryId) {
        return database.delete(MySQLiteHelper.TABLE_CATEGORY,
                MySQLiteHelper.COLUMN_ID_CATE + " = ?", new String[]{String.valueOf(categoryId)});
    }

    public Cursor getAllCategories() {
        open();
        return database.query(MySQLiteHelper.TABLE_CATEGORY, null,
                null, null, null, null, null);
    }
}
