package com.example.foodapp.Database.DataSource;

import static com.example.foodapp.Database.MySQLiteHelper.TABLE_ADMIN;
import static com.example.foodapp.Database.MySQLiteHelper.TABLE_USER;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.foodapp.Database.MySQLiteHelper;
import com.example.foodapp.Database.Entity.Admin;
import com.example.foodapp.Database.Entity.User;

import java.util.ArrayList;
import java.util.List;

public class AdminDataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    private String[] allColumns = {MySQLiteHelper.COLUMN_ID_ADMIN,
            MySQLiteHelper.COLUMN_NAME_ADMIN,
            MySQLiteHelper.COLUMN_PW_ADMIN
    };

    public AdminDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Admin insertUser(String name, String pw) {
        this.open();
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_NAME_ADMIN, name);
        values.put(MySQLiteHelper.COLUMN_PW_ADMIN, pw);

        long insertId = database.insert(TABLE_ADMIN, null, values);

        Cursor cursor = database.query(TABLE_ADMIN,
                allColumns, MySQLiteHelper.COLUMN_ID_ADMIN + " = " +
                        insertId, null, null, null, null);
        cursor.moveToFirst();
        Admin newAdmin = cursorToAdmin(cursor);
        cursor.close();
        this.close();
        return newAdmin;
    }

    public void deleteAdmin(Admin admin) {
        long id = admin.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(TABLE_ADMIN, MySQLiteHelper.COLUMN_ID_ADMIN
                + " = " + id, null);
    }


    private Admin cursorToAdmin(Cursor cursor) {
        Admin admin = new Admin();
        admin.setId(cursor.getInt(0));
        admin.setName(cursor.getString(1));
        admin.setPassword(cursor.getString(2));
        return admin;
    }
}
