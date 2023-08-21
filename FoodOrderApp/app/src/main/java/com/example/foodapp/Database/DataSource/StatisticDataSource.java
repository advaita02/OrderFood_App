//package com.example.foodapp.Database.DataSource;
//
//import android.content.Context;
//import android.database.Cursor;
//import android.database.SQLException;
//import android.database.sqlite.SQLiteDatabase;
//
//import com.example.foodapp.Database.Entity.OrderItem;
//import com.example.foodapp.Database.MySQLiteHelper;
//
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
//    public ArrayList<OrderItem> getBestSellingItems() {
//        ArrayList<OrderItem> orderItems = new ArrayList<>();
//        open();
//        Cursor cursor = database.rawQuery("SELECT " + MySQLiteHelper.COLUMN_FOOD_ORDER +
//                ", SUM(" + MySQLiteHelper.COLUMN_QUANTITY + ") AS total_quantity " +
//                "FROM " + MySQLiteHelper.TABLE_ORDER_ITEM +
//                " GROUP BY " + MySQLiteHelper.COLUMN_FOOD_ORDER +
//                " ORDER BY total_quantity DESC", null);
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            OrderItem orderItem = cursorToOrderItem(cursor);
//            orderItems.add(orderItem);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        close();
//        return orderItems;
//    }
//
//}
