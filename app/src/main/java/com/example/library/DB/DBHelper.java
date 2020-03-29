package com.example.library.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final int VERSION = 1;

    public static final String DB_NAME = "library.db";

    public static final String TABLE_NAME = "library";

    public static final String ID = "id";

    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "Password";
    public static final String SECTION = "section";
    public static final String PHONE = "phone";



    // create table sql query
    public String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + EMAIL + " TEXT," + NAME + " TEXT,"
            + PHONE + " TEXT," + PASSWORD + " TEXT," + SECTION + " TEXT"

            + ")";

    // drop table sql query
    public String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DBHelper(Context context){
        super(context, DB_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(CREATE_USER_TABLE);
        } catch (Exception e) {
            e.printStackTrace();


        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try{
            sqLiteDatabase.execSQL(DROP_USER_TABLE);
            onCreate(sqLiteDatabase);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
