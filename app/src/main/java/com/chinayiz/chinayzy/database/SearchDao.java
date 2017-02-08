package com.chinayiz.chinayzy.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.litesuits.orm.db.DataBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/7.
 */

public class SearchDao {
    private static SearchDabase dataBase;
    private static final String TAG="DbDao";
    private static final int DOWNLOAD=0;
    private static final int UPLOAD=1;
    private static final int RESULT=2;
    private static final int SELE=3;

    public static void getInstance(Context context){
        if (dataBase==null) {
            dataBase = new SearchDabase(context);
        }
    }

    /**
     * 添加
     */
    public static void add(String  title){
        SQLiteDatabase db=dataBase.getWritableDatabase();
        if (db.isOpen()){
            Log.i(TAG, "insert---------------------");
            ContentValues values=putBean(title);
            db.insert(SearchDabase.TABLE, null, values);
        }


    }

    /**
     * 删除
     */
    public static void deleteAll(){
        SQLiteDatabase db=dataBase.getWritableDatabase();
        if (db.isOpen()){
            Log.i(TAG, "delete---------------------");
            db.delete(SearchDabase.TABLE, null,null);

        }
    }

    //查询数据库所有数据
    public static List<String> findall(){
        SQLiteDatabase db=dataBase.getReadableDatabase();
        List<String> lists=new ArrayList<>();
        if (db.isOpen()){
            Cursor cursor=null;
            Log.i(TAG,"query---------------------");
            cursor=db.query(SearchDabase.TABLE, null, null, null, null, null, null);
            while (cursor.moveToNext()){
                String bean=getBean(cursor);
                lists.add(bean);
            }
            cursor.close();
        }
        return lists;
    }

    //查询数据库所有数据
    public static boolean findTitle(String title){
        SQLiteDatabase db=dataBase.getReadableDatabase();
        List<String> lists=new ArrayList<>();
        if (db.isOpen()){
            Cursor cursor=null;
            Log.i(TAG,"query---------------------");
            cursor=db.query(SearchDabase.TABLE, null, "title=?", new String[]{title},null, null, null);
            while (cursor.moveToNext()){
               if (cursor.getString(cursor.getColumnIndex("title"))!=null){
                   cursor.close();
                   return true;
               }
            }
            cursor.close();
        }
        return false;
    }



    public static ContentValues putBean(String title){
        ContentValues values=new ContentValues();
        values.put("title",title);
        return values;
    }

    public static String getBean(Cursor cursor){
         String title=cursor.getString(cursor.getColumnIndex("title"));
        return title;
    }



}
