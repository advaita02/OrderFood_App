package com.example.foodapp.Database.DataSource;

import static com.example.foodapp.Database.MySQLiteHelper.COLUMN_DATE;
import static com.example.foodapp.Database.MySQLiteHelper.COLUMN_ID_ORDER;
import static com.example.foodapp.Database.MySQLiteHelper.COLUMN_ID_USER;
import static com.example.foodapp.Database.MySQLiteHelper.COLUMN_NAME_USER;
import static com.example.foodapp.Database.MySQLiteHelper.COLUMN_PN_USER;
import static com.example.foodapp.Database.MySQLiteHelper.COLUMN_PW_USER;
import static com.example.foodapp.Database.MySQLiteHelper.COLUMN_USER_ORDER;
import static com.example.foodapp.Database.MySQLiteHelper.TABLE_ORDER;
import static com.example.foodapp.Database.MySQLiteHelper.TABLE_USER;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.foodapp.Database.Entity.Order;
import com.example.foodapp.Database.Entity.User;
import com.example.foodapp.Database.MySQLiteHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDataSource {
    Context context;
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");


    private String[] allColumns = {MySQLiteHelper.COLUMN_ID_ORDER,
            MySQLiteHelper.COLUMN_DATE,
            MySQLiteHelper.COLUMN_USER_ORDER
    };

    public OrderDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Order insertOrder(Integer id, String date) throws ParseException {
        open();
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_DATE, date);
        values.put(MySQLiteHelper.COLUMN_USER_ORDER, id);

        long insertId = database.insert(TABLE_ORDER, null, values);

        Cursor cursor = database.query(MySQLiteHelper.TABLE_ORDER,
                allColumns, COLUMN_ID_ORDER + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Order newOrder = cursorToOrder(cursor);
        cursor.close();
        close();

        return newOrder;
    }

    public ArrayList<Order> getOrdersByUser(int userId) throws ParseException {
        ArrayList<Order> orders = new ArrayList<>();
        open();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_ORDER, null,
                MySQLiteHelper.COLUMN_USER_ORDER + " = ?", new String[]{String.valueOf(userId)},
                null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Order order = cursorToOrder(cursor);
            orders.add(order);
            cursor.moveToNext();
        }
        cursor.close();
        close();
        return orders;
    }

    public ArrayList<Order> getOrdersByDate(String date) throws ParseException {
        ArrayList<Order> orders = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT * FROM " + MySQLiteHelper.TABLE_ORDER +
                " WHERE " + MySQLiteHelper.COLUMN_DATE + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{date});

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Order order = cursorToOrder(cursor);
            orders.add(order);
            cursor.moveToNext();
        }
        cursor.close();
        return orders;
    }

    public ArrayList<Order> getOrdersByDateRange(String startDate, String endDate) throws ParseException {
        ArrayList<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_ORDER +
                " WHERE " + COLUMN_DATE + " BETWEEN ? AND ?";

        Cursor cursor = database.rawQuery(query, new String[]{startDate, endDate});
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Order order = cursorToOrder(cursor);
            orders.add(order);
            cursor.moveToNext();
        }

        cursor.close();
        return orders;
    }



    private Order cursorToOrder(Cursor cursor) throws ParseException {
        Order order = new Order();
        order.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_ORDER)));
        order.setDate(dateFormat.parse(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE))));

        User user = new User();
        user.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_ORDER)));
        order.setUser(user);
        return order;
    }
    public Cursor getAllOrders() {
        return database.query(TABLE_ORDER, null, null,
                null, null, null, null);
    }
    public ArrayList<Order> getOrdersByUserId(Integer userId) throws ParseException {
        ArrayList<Order> orders = new ArrayList<>();
        open();

        Cursor cursor = database.query(
                MySQLiteHelper.TABLE_ORDER,
                allColumns,
                MySQLiteHelper.COLUMN_USER_ORDER + " = ?",
                new String[] { String.valueOf(userId) },
                null,
                null,
                null
        );

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Order order = cursorToOrder(cursor);
            orders.add(order);
            cursor.moveToNext();
        }

        cursor.close();
        close();

        return orders;
    }
    @SuppressLint("Range")
    public int getLatestOrderId() {
        open();
        Cursor cursor = database.query(
                MySQLiteHelper.TABLE_ORDER,
                new String[] { MySQLiteHelper.COLUMN_ID_ORDER },
                null,
                null,
                null,
                null,
                MySQLiteHelper.COLUMN_ID_ORDER + " DESC",
                "1"
        );
        int latestOrderId = -1;
        if (cursor != null && cursor.moveToFirst()) {
            latestOrderId = cursor.getInt(cursor.getColumnIndex(MySQLiteHelper.COLUMN_ID_ORDER));
            cursor.close();
        }
        close();
        return latestOrderId;
    }
//    @SuppressLint("Range")
//    public Order getOrderById(int userId) throws ParseException {
//        open();
//        String[] columns = {COLUMN_ID_ORDER, COLUMN_DATE, COLUMN_USER_ORDER};
//        String selection = COLUMN_USER_ORDER + " = ?";
//        String[] selectionArgs = {String.valueOf(userId)};
//
//        Cursor cursor = database.query(TABLE_ORDER, columns, selection, selectionArgs, null, null, null);
//
//        Order order = null;
//        if (cursor.moveToFirst()) {
//            order = new Order();
//            order.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID_ORDER)));
//            order.setDate(dateFormat.parse(cursor.getString(cursor.getColumnIndex(COLUMN_DATE))));
//            User user = new User();
//            user.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_ORDER)));
//            order.setUser(user);
//
//        }
//
//        cursor.close();
//        return order;
//    }
}
