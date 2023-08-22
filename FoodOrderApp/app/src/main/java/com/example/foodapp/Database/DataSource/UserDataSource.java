package com.example.foodapp.Database.DataSource;

import static com.example.foodapp.Database.MySQLiteHelper.COLUMN_ID_USER;
import static com.example.foodapp.Database.MySQLiteHelper.COLUMN_NAME_USER;
import static com.example.foodapp.Database.MySQLiteHelper.COLUMN_PN_USER;
import static com.example.foodapp.Database.MySQLiteHelper.COLUMN_PW_USER;
import static com.example.foodapp.Database.MySQLiteHelper.TABLE_USER;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.foodapp.Database.Entity.Category;
import com.example.foodapp.Database.MySQLiteHelper;
import com.example.foodapp.Database.Entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    private String[] allColumns = {MySQLiteHelper.COLUMN_ID_USER,
            MySQLiteHelper.COLUMN_NAME_USER,
            MySQLiteHelper.COLUMN_PN_USER,
            MySQLiteHelper.COLUMN_PW_USER
    };

    public UserDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public User insertUser(String name, Integer pn, String pw) {
        open();
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_NAME_USER, name);
        values.put(MySQLiteHelper.COLUMN_PN_USER, pn);
        values.put(MySQLiteHelper.COLUMN_PW_USER, pw);

        long insertId = database.insert(TABLE_USER, null, values);

        Cursor cursor = database.query(TABLE_USER,
                allColumns, MySQLiteHelper.COLUMN_ID_USER + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        User newUser = cursorToUser(cursor);
        cursor.close();
        close();
        return newUser;
    }

    public void deleteUser(User user) {
        long id = user.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(TABLE_USER, MySQLiteHelper.COLUMN_ID_USER
                + " = " + id, null);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();

        Cursor cursor = database.query(TABLE_USER, allColumns, null,
                null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            User user = cursorToUser(cursor);
            users.add(user);
            cursor.moveToNext();
        }
        cursor.close();
        return users;
    }

    private User cursorToUser(Cursor cursor) {
        User user = new User();
        user.setId(cursor.getInt(0));
        user.setName(cursor.getString(1));
        user.setPn(cursor.getInt(2));
        user.setPw(cursor.getString(3));
        return user;
    }
    @SuppressLint("Range")
    public User getUserById(int userId) {
        open();
        String[] columns = {COLUMN_ID_USER, COLUMN_NAME_USER, COLUMN_PN_USER, COLUMN_PW_USER};
        String selection = COLUMN_ID_USER + " = ?";
        String[] selectionArgs = {String.valueOf(userId)};

        Cursor cursor = database.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);

        User user = null;
        if (cursor.moveToFirst()) {
            user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID_USER)));
            user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_USER)));
            user.setPn(cursor.getInt(cursor.getColumnIndex(COLUMN_PN_USER)));
            user.setPw(cursor.getString(cursor.getColumnIndex(COLUMN_PW_USER)));
        }

        cursor.close();
        return user;
    }


    public boolean checkPN(Integer pn) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {COLUMN_PN_USER};
        String selection = COLUMN_PN_USER + " = ?";
        String[] selectionArgs = {Integer.toString(pn)};

        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);

        boolean userExists = cursor.getCount() > 0;
        cursor.close();
        return userExists;
    }


    public Boolean checkPNPW(Integer pn, String pw) {
        SQLiteDatabase sqLiteDatabase = this.dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_USER + " where " + COLUMN_PN_USER
                + " = ? and " + COLUMN_PW_USER + " = ?", new String[]{Integer.toString(pn), pw});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public Boolean phoneFormatCheck (String pn){
        try {
            Integer.parseInt(pn);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public String loginInfo(Integer pn){
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_USER + " where " + COLUMN_PN_USER
                + " = ? ", new String[]{Integer.toString(pn)});

        if (cursor.moveToFirst()) {
            @SuppressLint("Range")
            String userName = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_USER));
            cursor.close();
            return userName;
        } else {
            cursor.close();
            return null;
        }
    }
    public void editNameUser(Integer phoneNum,String newName){
        SQLiteDatabase sqLiteDatabase = this.dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_USER,newName);

        String whereClause = COLUMN_PN_USER + " = ?";
        String[] whereArgs = {String.valueOf(phoneNum)};

        sqLiteDatabase.update(TABLE_USER,values,whereClause,whereArgs);
        sqLiteDatabase.close();
    }
    public Integer getIdUser (Integer pn){
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_USER + " where " + COLUMN_PN_USER
                + " = ? ", new String[]{Integer.toString(pn)});

        if (cursor.moveToFirst()) {
            @SuppressLint("Range")
            int idUser = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID_USER)));
            cursor.close();
            return idUser;
        } else {
            cursor.close();
            return null;
        }
    }

}
