//package com.example.foodapp.Database.DataSource;
//
//import static com.example.foodapp.Database.DataSource.OrderDataSource.dateFormat;
//import static com.example.foodapp.Database.MySQLiteHelper.COLUMN_DATE;
//import static com.example.foodapp.Database.MySQLiteHelper.COLUMN_ID_FOOD;
//import static com.example.foodapp.Database.MySQLiteHelper.COLUMN_ID_ORDER;
//import static com.example.foodapp.Database.MySQLiteHelper.COLUMN_ID_ORDER_ITEM;
//import static com.example.foodapp.Database.MySQLiteHelper.COLUMN_ID_USER;
//
//import android.content.Context;
//import android.database.Cursor;
//import android.database.SQLException;
//import android.database.sqlite.SQLiteDatabase;
//
//import com.example.foodapp.Database.Entity.Food;
//import com.example.foodapp.Database.Entity.Order;
//import com.example.foodapp.Database.Entity.OrderItem;
//import com.example.foodapp.Database.Entity.User;
//import com.example.foodapp.Database.MySQLiteHelper;
//
//import java.text.ParseException;
//import java.util.ArrayList;
//
//public class StatisticDataSource {
//    private SQLiteDatabase database;
//    private MySQLiteHelper dbHelper;
//
//    public StatisticDataSource(Context context) {
//        dbHelper = new MySQLiteHelper(context);
//    }
//
//    public void open() throws SQLException {
//        database = dbHelper.getWritableDatabase();
//    }
//
//    public void close() {
//        dbHelper.close();
//    }
//
//    private Order cursorToOrder(Cursor cursor) throws ParseException {
//        Order order = new Order();
//        order.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_ORDER)));
//        order.setDate(dateFormat.parse(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE))));
//
//        User user = new User();
//        user.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_USER)));
//        order.setUser(user);
//        return order;
//    }
//
//    private OrderItem cursorToOrderItem(Cursor cursor) {
//        OrderItem orderItem = new OrderItem();
//        orderItem.setId(cursor.getInt(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_ID_ORDER_ITEM)));
//        int foodId = cursor.getInt(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_FOOD_ORDER));
//        int quantity = cursor.getInt(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_QUANTITY));
//        // Fetch the food details based on foodId and set them to the orderItem
//        Food food = new Food();
//        food.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_FOOD)));
//        orderItem.or
//        orderItem.setQuantity(quantity);
//        return orderItem;
//    }
//
//}
