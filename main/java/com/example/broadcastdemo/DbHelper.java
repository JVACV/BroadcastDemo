package com.example.broadcastdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME ="numberDb";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE = "Create table " + DbContact.TABLE_NAME + "(id integer primary key autoincrement," + DbContact.INCOMING_NUMBER + " text);";
    private static  final String DROP_TABLE = "drop table if exists " + DbContact.TABLE_NAME;
    public DbHelper(Context context) {

        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);

    }
    public void saveNumber(String number,SQLiteDatabase db)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbContact.INCOMING_NUMBER,number);
        db.insert(DbContact.TABLE_NAME,null,contentValues);

    }
    public Cursor readNumber(SQLiteDatabase db){
        String[] projection = { "id",DbContact.INCOMING_NUMBER};
        return (db.query(DbContact.TABLE_NAME,projection,null,null,null,null,null));
    }
}
