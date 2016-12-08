package com.example.mju.embeded;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mju.embeded.login.Login_Contract;

/**
 * Created by Only user one on 2016-12-05.
 */


public class myDBHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;

    public myDBHelper(Context context)
    {
        super(context, Login_Contract.DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db)
    {
        //db.execSQL(Login_Contract.SQL_DELETE_TABLE);
        //db.execSQL(Post_Contract.SQL_DELETE_TABLE);
        db.execSQL(Login_Contract.SQL_CREATE_TABLE);
        db.execSQL(Post_Contract.SQL_CREATE_TABLE);
        db.execSQL(Apply_Contract.SQL_CREATE_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //db.execSQL(Login_Contract.SQL_DELETE_TABLE);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //db.execSQL(Login_Contract.SQL_DELETE_TABLE);
    }
}
