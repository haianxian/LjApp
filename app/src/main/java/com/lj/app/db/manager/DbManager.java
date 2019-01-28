package com.lj.app.db.manager;

import android.database.sqlite.SQLiteDatabase;

import com.lj.app.base.app.MyApplication;
import com.lj.app.config.AppConfig;
import com.lj.app.db.gen.DaoMaster;
import com.lj.app.db.gen.DaoSession;

/**
 * Created by 13717 on 2017/12/5.
 */

public class DbManager {

    public static final String DBNAME = AppConfig.DATABASE_NAME;
    private static DbManager dbManager;
    private SQLiteDatabase mSQLiteDatabase;
//    GreenDao的顶级对象，作为数据库对象、用于创建表和删除表
    private DaoMaster mDaoMaster;
//    管理所有的Dao对象，Dao对象中存在着增删改查等API
    private DaoSession mDaoSession;

    private DbManager(){
        mSQLiteDatabase = new DbUpgradeHelper(MyApplication.getInstance().getApplicationContext(), DBNAME).getReadableDatabase();
        mDaoMaster = new DaoMaster(mSQLiteDatabase);
        mDaoSession = mDaoMaster.newSession();
    }

    public static DbManager getInstance(){
        if(dbManager == null){
            dbManager = new DbManager();
        }
        return dbManager;
    }

    public SQLiteDatabase getSQLiteDatabase() {
        return mSQLiteDatabase;
    }

    public DaoMaster getDaoMaster() {
        return mDaoMaster;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
