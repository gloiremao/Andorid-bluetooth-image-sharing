package com.example.android.bluetoothchat;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mao on 6/5/16.
 */
public class SQLiteHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "mygallery.db";
    public static final String TABLE_NAME = "myImageTable";
    public static final int VERSION = 1;
    private static SQLiteDatabase database;

    public static String col_size = "size";
    public static String col_Uri = "Uri";
    public static String col_lastUpdate = "lastUpdate";


    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                col_size + " TEXT, " +
                col_Uri + " TEXT, " +
                col_lastUpdate + " TEXT)";
        db.execSQL(SQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL = "DROP TABLE " + TABLE_NAME;
        db.execSQL(SQL);
    }
}
