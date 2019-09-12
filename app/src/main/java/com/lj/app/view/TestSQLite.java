package com.lj.app.view;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lj.app.R;
import com.lj.app.db.SQLiteDbHelper;

public class TestSQLite extends Activity {

    SQLiteDatabase db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sqlite);
        SQLiteDbHelper dbHelper = new SQLiteDbHelper(this);
        db = dbHelper.getWritableDatabase();

    }

    // 插入
    private void insert(String sessionId,String flag){
        ContentValues values = new ContentValues();
        values.put("sessionId",flag);
        db.insert(SQLiteDbHelper.TABLE_NAME,null,values);
    }

    //修改
    private void update(String sessionId,String flag){
        ContentValues values = new ContentValues();
        values.put("sessionId",flag);
        db.update(SQLiteDbHelper.TABLE_NAME,values,"sessionId = ? ",new String[]{flag});
    }

    //查询
    private void query(){
        Cursor cursor = db.query(SQLiteDbHelper.TABLE_NAME,new String[]{"sessionId"},null,null,null,null,null);
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
        }
        cursor.close();
    }
}
