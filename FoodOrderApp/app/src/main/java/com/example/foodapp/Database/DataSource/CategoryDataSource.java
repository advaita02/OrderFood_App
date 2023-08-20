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

    public Category getCategoryById(int categoryId) {
        String[] projection = {
                MySQLiteHelper.COLUMN_ID_CATE,
                MySQLiteHelper.COLUMN_IMG_CATE,
                MySQLiteHelper.COLUMN_NAME_CATE
        };

        String selection = MySQLiteHelper.COLUMN_ID_CATE + " = ?";
        String[] selectionArgs = { String.valueOf(categoryId) };

        Cursor cursor = database.query(
                MySQLiteHelper.TABLE_CATEGORY,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        Category category = null;
        if (cursor != null && cursor.moveToFirst()) {
            category = cursorToCategory(cursor);
            cursor.close();
        }

        return category;
    }

    @SuppressLint("Range")
    private Category cursorToCategory(Cursor cursor) {
        Category category = new Category();
        category.setId(cursor.getInt(cursor.getColumnIndex(MySQLiteHelper.COLUMN_ID_CATE)));
        category.setName(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_NAME_CATE)));
        // Assuming you have a method to convert BLOB to byte[] for image
        category.setImg_cate(cursor.getBlob(cursor.getColumnIndex(MySQLiteHelper.COLUMN_IMG_CATE)));
        return category;
    }
}
