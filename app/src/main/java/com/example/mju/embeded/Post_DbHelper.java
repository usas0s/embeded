package com.example.mju.embeded;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class Post_DbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;

    public Post_DbHelper(Context context)
    {
        super(context, Post_Contract.DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(Post_Contract.SQL_PRAGMA_ON);
        db.execSQL(Post_Contract.SQL_DELETE_TABLE);
        db.execSQL(Post_Contract.SQL_CREATE_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //db.execSQL(Post_Contract.SQL_DELETE_TABLE);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(Post_Contract.SQL_DELETE_TABLE);
    }


}