package com.example.library.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static com.example.library.DB.DBHelper.EMAIL;
import static com.example.library.DB.DBHelper.NAME;
import static com.example.library.DB.DBHelper.PASSWORD;
import static com.example.library.DB.DBHelper.SECTION;
import static com.example.library.DB.DBHelper.PHONE;
import static com.example.library.DB.DBHelper.TABLE_NAME;


public class DBAdapter {
    SQLiteDatabase db;
    DBHelper myhelper;

    public DBAdapter(Context context) {
        myhelper = new DBHelper(context);
        db = myhelper.getWritableDatabase();

    }

    public void insertData(String name, String email, String pass,  String phone, String section) {

        ContentValues values = new ContentValues();
        values.put(NAME, name);

        values.put(EMAIL, email);
        values.put(PASSWORD, pass);
        values.put(PHONE, phone);
        values.put(SECTION, section);

        db.insert(DBHelper.TABLE_NAME, null, values);

        db.close();
    }

    public void updateData(String email, String name, String pass,  String phone, String section) {

        ContentValues values = new ContentValues();
        values.put(NAME, name);

        values.put(EMAIL, email);
        values.put(PASSWORD, pass);
        values.put(PHONE,phone);
        values.put(SECTION, section);

        String[] arrayOfWhere = {email};
        db.update(TABLE_NAME, values, EMAIL + " = ?", arrayOfWhere);
        db.close();
    }

    public String[] getData(String mail) {
        //  final String SQL_SELECT_QUERY = "SELECT * FROM "+ TABLE_USER+" WHERE "+ COLUMN_USER_EMAIL+ "= +"mail;

        db = myhelper.getReadableDatabase();
        String[] columns = {

                PHONE,
                SECTION,
                EMAIL,
                PASSWORD,
                NAME};
        String selection = EMAIL + " = ?";

        String[] selectionArgs = {mail};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        String[] buffer = new String[columns.length];
        while (cursor.moveToNext()) {
            String email = cursor.getString(cursor.getColumnIndex(EMAIL));
            String name = cursor.getString(cursor.getColumnIndex(NAME));
            String section = cursor.getString(cursor.getColumnIndex(SECTION));
            String phone = cursor.getString(cursor.getColumnIndex(PHONE));

            String password = cursor.getString(cursor.getColumnIndex(PASSWORD));
            buffer[0] = email;
            buffer[1] = name;
            buffer[2] = section;
            buffer[3] = phone;

            buffer[4] = password;
        }
        cursor.close();
        return buffer;
    }
}
