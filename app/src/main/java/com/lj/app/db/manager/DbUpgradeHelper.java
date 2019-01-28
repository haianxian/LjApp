package com.lj.app.db.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.lj.app.db.gen.DaoMaster;

import org.greenrobot.greendao.database.Database;

/**
 * Created by 13717 on 2017/12/5.
 */

public class DbUpgradeHelper extends DaoMaster.OpenHelper{

    public DbUpgradeHelper(Context context, String name) {
        super(context, name);
    }

    public DbUpgradeHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        super.onUpgrade(db, oldVersion, newVersion);
//    }


    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        switch(newVersion){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }
}
