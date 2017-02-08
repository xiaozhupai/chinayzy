package com.chinayiz.chinayzy.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/2/7.
 */

public class SearchDabase extends SQLiteOpenHelper {
    public static final String TABLE="SEARCHDATA";

    public SearchDabase(Context context) {
        super(context, "downloads.db", null, 1);
    }

    public static final String sql="(id integer primary key autoincrement," +
            "title varchar (50))";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE+sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
