package com.lj.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "database.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "friend";

    private static String CREATE_TABLE_FRIEND = "create table "+TABLE_NAME
            + " (id integer primary key autoincrement,"
            +"sessionId varchar(64) not null,"
            +"flag varchar(20) not null"
            +");";
    public SQLiteDbHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FRIEND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
