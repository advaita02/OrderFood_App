package com.example.foodapp.Database.DataSource;

import static com.example.foodapp.Database.MySQLiteHelper.COLUMN_DATE;
import static com.example.foodapp.Database.MySQLiteHelper.COLUMN_ID_ORDER;
import static com.example.foodapp.Database.MySQLiteHelper.COLUMN_ID_USER;
import static com.example.foodapp.Database.MySQLiteHelper.TABLE_ORDER;
import static com.example.foodapp.Database.MySQLiteHelper.TABLE_USER;

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

    public long insertOrder(Integer id, String date) {

        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_DATE, date);
        values.put(MySQLiteHelper.COLUMN_USER_ORDER, id);

        long insertId = database.insert(TABLE_ORDER, null, values);

        return insertId;
    }
    private Order cursorToOrder(Cursor cursor) throws ParseException {
        Order order = new Order();
        order.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_ORDER)));
        order.setDate(dateFormat.parse(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE))));

        User user = new User();
        user.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_USER)));
        order.setUser(user);
        return order;
    }
    public Cursor getAllOrders() {
        return database.query(TABLE_ORDER, null, null,
                null, null, null, null);
    }
}
