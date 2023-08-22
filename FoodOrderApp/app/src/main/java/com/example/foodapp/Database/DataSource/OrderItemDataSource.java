package com.example.foodapp.Database.DataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.foodapp.Database.Entity.Food;
import com.example.foodapp.Database.Entity.Order;
import com.example.foodapp.Database.Entity.OrderItem;
import com.example.foodapp.Database.MySQLiteHelper;

import java.util.ArrayList;

public class OrderItemDataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    private String[] allColumns = {
            MySQLiteHelper.COLUMN_ID_ORDER_ITEM,
            MySQLiteHelper.COLUMN_ORDER,
            MySQLiteHelper.COLUMN_FOOD_ORDER,
            MySQLiteHelper.COLUMN_QUANTITY
    };

    public OrderItemDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public OrderItem insertOrderItem(int orderID, int foodID, String quantity) {
        open();
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_ORDER, orderID);
        values.put(MySQLiteHelper.COLUMN_FOOD_ORDER, foodID);
        values.put(MySQLiteHelper.COLUMN_QUANTITY, quantity);

        long insertId = database.insert(MySQLiteHelper.TABLE_ORDER_ITEM, null, values);

        Cursor cursor = database.query(MySQLiteHelper.TABLE_ORDER_ITEM,
                allColumns, MySQLiteHelper.COLUMN_ID_ORDER_ITEM + " = " + insertId, null, null, null, null);

        cursor.moveToFirst();
        OrderItem newOrderItem = cursorToOrderItem(cursor);
        cursor.close();
        close();
        return newOrderItem;
    }

    public void deleteOrderItem(OrderItem orderItem) {
        long id = orderItem.getId();
        System.out.println("Order item deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_ORDER_ITEM,
                MySQLiteHelper.COLUMN_ID_ORDER_ITEM + " = " + id, null);
    }

    private OrderItem cursorToOrderItem(Cursor cursor) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(cursor.getInt(0));

        Order order = new Order();
        order.setId(cursor.getInt(1));
        orderItem.setOrder_id(order);

        Food food = new Food();
        food.setId(cursor.getInt(2));
        orderItem.setQuantity(Integer.parseInt(cursor.getString(3)));
        return orderItem;
    }
}

