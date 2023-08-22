package com.example.foodapp.Database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;
public class MySQLiteHelper extends SQLiteOpenHelper{
    // database
    private static final String DATABASE_NAME = "FoodOrdering.db";
    private static final int DATABASE_VERSION = 1;

    // table food
    public static final String TABLE_FOOD = "food"; // name of table
    public static final String COLUMN_ID_FOOD = "id_food";
    public static final String COLUMN_NAME_FOOD = "name_food";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_DESCRIBE = "describe";
    public static final String COLUMN_SIZE = "size";
    public static final String COLUMN_IMG_FOOD = "img_food";
    public static final String COLUMN_CATEGORY = "id_category";

    // table category
    public static final String TABLE_CATEGORY = "category"; // name of table
    public static final String COLUMN_ID_CATE = "id_category";
    public static final String COLUMN_NAME_CATE = "name_category";
    public static final String COLUMN_IMG_CATE = "img_cate";
    // table category

    //table rating
    public static final String TABLE_RATING = "rating"; // name of table
    public static final String COLUMN_ID_RATE = "id_rating";
    public static final String COLUMN_VALUE = "value";
    public static final String COLUMN_COMMENT = "comment";
    public static final String COLUMN_RATED_USER = "user_rate_id";
    public static final String COLUMN_RATED_FOOD = "food_rate_id";

    //table order
    public static final String TABLE_ORDER = "orders"; // name of table
    public static final String COLUMN_ID_ORDER = "id_order";
    public static final String COLUMN_DATE = "date_order";
    public static final String COLUMN_USER_ORDER = "order_user_id";

    //TABLE order_item
    public static final String TABLE_ORDER_ITEM = "order_item";
    public static final String COLUMN_ID_ORDER_ITEM = "id_order_items";
    public static final String COLUMN_ORDER = "order_order_item";
    public static final String COLUMN_FOOD_ORDER = "food";
    public static final String COLUMN_QUANTITY = "quantity";


    //table cus_user
    public static final String TABLE_USER = "user";
    public static final String COLUMN_ID_USER = "id_user";
    public static final String COLUMN_NAME_USER = "name_user";
    public static final String COLUMN_PN_USER = "pn_user";
    public static final String COLUMN_PW_USER = "pw_user";

    public static final String TABLE_ADMIN = "admin";
    public static final String COLUMN_ID_ADMIN = "id_admin";
    public static final String COLUMN_NAME_ADMIN = "name_admin";
    public static final String COLUMN_PW_ADMIN = "pw_admin";



    // SQL create table
    private static final String ORDER_ITEM_CREATE = "create table "
            + TABLE_ORDER_ITEM + "(" + COLUMN_ID_ORDER_ITEM + " integer primary key autoincrement, "
            + COLUMN_ORDER + " integer references " + TABLE_ORDER + "(" + COLUMN_ID_ORDER + "), "
            + COLUMN_FOOD_ORDER + " integer references " + TABLE_FOOD + "(" + COLUMN_ID_FOOD + "), "
            + COLUMN_QUANTITY + " TEXT);";

    private static final String RATING_CREATE = "create table "
            + TABLE_RATING + "(" + COLUMN_ID_RATE + " integer primary key autoincrement, "
            + COLUMN_VALUE + " integer, "
            + COLUMN_COMMENT + " TEXT, "
            + COLUMN_RATED_USER + " integer references " + TABLE_USER + "(" + COLUMN_ID_USER + "), "
            + COLUMN_RATED_FOOD + " integer references " + TABLE_FOOD + "(" + COLUMN_ID_FOOD + "));";

    private static final String FOOD_CREATE = "create table "
            + TABLE_FOOD + "( " + COLUMN_ID_FOOD + " integer primary key autoincrement, "
            + COLUMN_NAME_FOOD + " text, "
            + COLUMN_PRICE + " integer not null, "
            + COLUMN_DESCRIBE + " text, "
            + COLUMN_SIZE + " integer, "
            + COLUMN_IMG_FOOD + " BLOB, "
            + COLUMN_CATEGORY + " integer references " + TABLE_CATEGORY + "(" + COLUMN_ID_CATE + "));";

    private static final String USER_CREATE = "create table "
            + TABLE_USER + "(" + COLUMN_ID_USER + " integer primary key autoincrement, "
            + COLUMN_NAME_USER + " TEXT, "
            + COLUMN_PN_USER + " integer, "
            + COLUMN_PW_USER + " TEXT);";

    private static final String ORDER_CREATE = "create table "
            + TABLE_ORDER + "(" + COLUMN_ID_ORDER + " integer primary key autoincrement, "
            + COLUMN_DATE + " TEXT, "
            + COLUMN_USER_ORDER + " integer references " + TABLE_USER + "(" + COLUMN_ID_USER + "));";

    private static final String CATEGORY_CREATE = "create table "
            + TABLE_CATEGORY + "(" + COLUMN_ID_CATE + " integer primary key autoincrement, "
            + COLUMN_IMG_CATE + " BLOB, "
            + COLUMN_NAME_CATE + " text not null);";


    private static final String ADMIN_CREATE = "create table "
            + TABLE_ADMIN + "(" + COLUMN_ID_ADMIN + " integer primary key autoincrement, "
            + COLUMN_NAME_ADMIN + " TEXT, "
            + COLUMN_PW_ADMIN + " TEXT);";

//    String themAdmin = "Insert into admin (name_admin, pw_admin) values('staff_admin', 123);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FOOD_CREATE);
        db.execSQL(CATEGORY_CREATE);
        db.execSQL(USER_CREATE);
        db.execSQL(ADMIN_CREATE);
        db.execSQL(RATING_CREATE);
        db.execSQL(ORDER_CREATE);
        db.execSQL(ORDER_ITEM_CREATE);
//        db.execSQL(themAdmin);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public Cursor GetData (String sql) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery(sql, null);
    }

    public void insertCategory(String categoryName) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_NAME_CATE, categoryName);
        sqLiteDatabase.insert(TABLE_CATEGORY, null, values);
        sqLiteDatabase.close();
    }

    public void insertFood(String nameFood, int priceFood, String desFood) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_NAME_FOOD, nameFood);
        values.put(MySQLiteHelper.COLUMN_PRICE, priceFood);
        values.put(MySQLiteHelper.COLUMN_DESCRIBE, desFood);
        sqLiteDatabase.insert(TABLE_FOOD, null, values);
        sqLiteDatabase.close();
    }

    public void deleteAllCate() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(TABLE_CATEGORY,null, null);
        sqLiteDatabase.close();
    }
    public Cursor GetImageCursor(int foodId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = { "img_food" }; // Thay "image_column_name" bằng tên cột chứa hình ảnh
        String selection = "id_food = ?";
        String[] selectionArgs = { String.valueOf(foodId) };

        return db.query(TABLE_FOOD, projection, selection, selectionArgs, null, null, null);
    }

}
