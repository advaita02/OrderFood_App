package com.example.foodapp.Database.DataSource;

import static com.example.foodapp.Database.MySQLiteHelper.COLUMN_CATEGORY;
import static com.example.foodapp.Database.MySQLiteHelper.COLUMN_DATE;
import static com.example.foodapp.Database.MySQLiteHelper.COLUMN_DESCRIBE;
import static com.example.foodapp.Database.MySQLiteHelper.COLUMN_FOOD_ORDER;
import static com.example.foodapp.Database.MySQLiteHelper.COLUMN_ID_FOOD;
import static com.example.foodapp.Database.MySQLiteHelper.COLUMN_ID_ORDER;
import static com.example.foodapp.Database.MySQLiteHelper.COLUMN_ID_ORDER_ITEM;
import static com.example.foodapp.Database.MySQLiteHelper.COLUMN_ID_USER;
import static com.example.foodapp.Database.MySQLiteHelper.COLUMN_NAME_FOOD;
import static com.example.foodapp.Database.MySQLiteHelper.COLUMN_ORDER;
import static com.example.foodapp.Database.MySQLiteHelper.COLUMN_PRICE;
import static com.example.foodapp.Database.MySQLiteHelper.COLUMN_QUANTITY;
import static com.example.foodapp.Database.MySQLiteHelper.COLUMN_SIZE;
import static com.example.foodapp.Database.MySQLiteHelper.COLUMN_USER_ORDER;
import static com.example.foodapp.Database.MySQLiteHelper.TABLE_ORDER;
import static com.example.foodapp.Database.MySQLiteHelper.TABLE_ORDER_ITEM;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


import com.example.foodapp.Database.Entity.Category;
import com.example.foodapp.Database.Entity.Food;
import com.example.foodapp.Database.Entity.Order;
import com.example.foodapp.Database.Entity.OrderItem;
import com.example.foodapp.Database.Entity.User;
import com.example.foodapp.Database.MySQLiteHelper;

import java.text.ParseException;
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


    public OrderItem insertOrderItem(Integer idOrder, Integer idFood,int quantity) throws ParseException {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ORDER, idOrder);
        values.put(COLUMN_FOOD_ORDER, idFood);
        values.put(COLUMN_QUANTITY,quantity);

        long insertId = database.insert(TABLE_ORDER_ITEM, null, values);

        Cursor cursor = database.query(TABLE_ORDER_ITEM,
                allColumns, COLUMN_ID_ORDER_ITEM + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        OrderItem newOrderItem = cursorToOrderItem(cursor);
        cursor.close();
        close();

        return newOrderItem;
    }
    private OrderItem cursorToOrderItem(Cursor cursor) throws ParseException {
        OrderItem orderItems = new OrderItem();
        orderItems.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_ORDER_ITEM)));

        Order order = new Order();
        order.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ORDER)));
        orderItems.setOrder_id(order);

        Food food = new Food();
        food.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FOOD_ORDER)));
        orderItems.setFood_id(food);

        orderItems.setQuantity(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY)));

        return orderItems;
    }

    public Cursor getAllOrderItems() {
        return database.query(TABLE_ORDER_ITEM, null, null,
                null, null, null, null);
    }
    public ArrayList<OrderItem> getOrderItemsByOrderId(Integer orderId) throws ParseException {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        open();

        Cursor cursor = database.query(
                TABLE_ORDER_ITEM,
                allColumns,
                COLUMN_ORDER + " = ?",
                new String[] { String.valueOf(orderId) },
                null,
                null,
                null
        );

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            OrderItem orderItems1 = cursorToOrderItems(cursor);
            orderItems.add(orderItems1);
            cursor.moveToNext();
        }

        cursor.close();
        close();

        return orderItems;
    }
//    private Food cursorToFood(Cursor cursor) throws ParseException {
//        Food food = new Food();
//        food.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_FOOD)));
//        food.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_FOOD)));
//        food.setPrice(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRICE)));
//        food.setDescribe(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIBE)));
//        food.setSize(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SIZE)));
//        food.setImgFood(cursor.getBlob(cursor.getColumnIndexOrThrow(COLUMN_SIZE)));
//
//        Category category = new Category();
//        category.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY)));
//        food.setCategory(category);
//
//        return food;
//    }
    private OrderItem cursorToOrderItems(Cursor cursor) throws ParseException {
        OrderItem orderItems = new OrderItem();
        orderItems.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_ORDER_ITEM)));

        Food food = new Food();
        food.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FOOD_ORDER)));
        orderItems.setFood_id(food);

        Order order = new Order();
        order.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ORDER)));
        orderItems.setOrder_id(order);


        return orderItems;
    }
}

//    public OrderItem insertOrderItem(int orderID, int foodID, String quantity) {
//        open();
//        ContentValues values = new ContentValues();
//        values.put(MySQLiteHelper.COLUMN_ORDER, orderID);
//        values.put(MySQLiteHelper.COLUMN_FOOD_ORDER, foodID);
//        values.put(MySQLiteHelper.COLUMN_QUANTITY, quantity);
//
//        long insertId = database.insert(MySQLiteHelper.TABLE_ORDER_ITEM, null, values);
//
//        Cursor cursor = database.query(MySQLiteHelper.TABLE_ORDER_ITEM,
//                allColumns, MySQLiteHelper.COLUMN_ID_ORDER_ITEM + " = " + insertId, null, null, null, null);
//
//        cursor.moveToFirst();
//        OrderItem newOrderItem = cursorToOrderItem(cursor);
//        cursor.close();
//        close();
//        return newOrderItem;
//    }
//
//    public void deleteOrderItem(OrderItem orderItem) {
//        long id = orderItem.getId();
//        System.out.println("Order item deleted with id: " + id);
//        database.delete(MySQLiteHelper.TABLE_ORDER_ITEM,
//                MySQLiteHelper.COLUMN_ID_ORDER_ITEM + " = " + id, null);
//    }
//
//    private OrderItem cursorToOrderItem(Cursor cursor) {
//        OrderItem orderItem = new OrderItem();
//        orderItem.setId(cursor.getInt(0));
//
//        Order order = new Order();
//        order.setId(cursor.getInt(1));
//        orderItem.setOrder_id(order);
//
//        Food food = new Food();
//        food.setId(cursor.getInt(2));
//        orderItem.setFood_id(food);
//
//        orderItem.setQuantity(Integer.parseInt(cursor.getString(3)));
//        return orderItem;
//    }
//}

