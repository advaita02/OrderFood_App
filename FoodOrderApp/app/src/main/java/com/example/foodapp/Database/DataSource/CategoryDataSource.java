package com.example.foodapp.Database.DataSource;

import android.database.sqlite.SQLiteDatabase;

import com.example.foodapp.Database.MySQLiteHelper;

public class CategoryDataSource {
    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID_FOOD,
            MySQLiteHelper.COLUMN_NAME_FOOD,
            MySQLiteHelper.COLUMN_PRICE
    };
}
