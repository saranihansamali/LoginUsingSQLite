package com.intpro.sqllitelogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Alpha";
    public static final String TABLE_NAME = "User";
    public static final String INDEX = "IndexNo";
    public static final String NAME = "Name";
    public static final String EMAIL = "Email";
    public static final String PHONE = "Phone";
    public static final String PASSWORD = "Password";

    public DBHelper( Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE User (IndexNo INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Email TEXT, Phone TEXT, Password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public long addUser(String index, String name, String email, String phone, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(INDEX, index);
        contentValues.put(NAME, name);
        contentValues.put(EMAIL, email);
        contentValues.put(PHONE, phone);
        contentValues.put(PASSWORD, password);

        long res = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return res;
    }

    public boolean checkUser(String index, String password)
    {
        String[] colmns = { INDEX };
        SQLiteDatabase db = getReadableDatabase();
        String select = INDEX + "=?" + " and " + PASSWORD + "=?";
        String[] selectArgs = {index, password};
        Cursor cursor = db.query(TABLE_NAME, colmns, select, selectArgs, null, null, null );
        int count = cursor.getCount();
        cursor.close();

        if(count > 0)
            return true;
        else
            return false;
    }

}
