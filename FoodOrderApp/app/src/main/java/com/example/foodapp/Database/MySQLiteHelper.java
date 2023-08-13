package com.example.foodapp.Database;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
    public static final String COLUMN_CATEGORY = "id_category";

    // table category
    public static final String TABLE_CATEGORY = "category"; // name of table
    public static final String COLUMN_ID_CATE = "id_category";
    public static final String COLUMN_NAME_CATE = "name_category";
    // table category

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
    private static final String FOOD_CREATE = "create table "
            + TABLE_FOOD + "( " + COLUMN_ID_FOOD + " integer primary key autoincrement, "
            + COLUMN_NAME_FOOD + "text,"
            + COLUMN_PRICE + " integer not null, "
            + COLUMN_DESCRIBE + " text, "
            + COLUMN_SIZE + "integer, "
            + COLUMN_CATEGORY + "integer references " + TABLE_CATEGORY + "(" + COLUMN_ID_CATE + "));";

    private static final String CATEGORY_CREATE = "create table "
            + TABLE_CATEGORY + "(" + COLUMN_ID_CATE + " integer primary key autoincrement, "
            + COLUMN_NAME_CATE + "text not null);";

    private static final String USER_CREATE = "create table "
            + TABLE_USER + "(" + COLUMN_ID_USER + " integer primary key autoincrement, "
            + COLUMN_NAME_USER + " TEXT, "
            + COLUMN_PN_USER + " integer, "
            + COLUMN_PW_USER + " TEXT)";

    private static final String ADMIN_CREATE = "create table "
            + TABLE_ADMIN + "(" + COLUMN_ID_ADMIN + " integer primary key autoincrement, "
            + COLUMN_NAME_ADMIN + " TEXT, "
            + COLUMN_PW_ADMIN + " TEXT)";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FOOD_CREATE);
        db.execSQL(CATEGORY_CREATE);
        db.execSQL(USER_CREATE);
        db.execSQL(ADMIN_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }
}
