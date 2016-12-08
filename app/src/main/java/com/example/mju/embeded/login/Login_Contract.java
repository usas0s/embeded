package com.example.mju.embeded.login;

import android.provider.BaseColumns;

/*
         ~ Copyright (C) 컴퓨터공학과 60132291 오지훈
*/

public final class Login_Contract {

    public Login_Contract(){};

    public static final String DATABASE_NAME = "DB";
    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + FeedEntry.TABLE_NAME + " ( " +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_ID + " text , " +
                    FeedEntry.COLUMN_NAME_PASS + " text ," +
                    FeedEntry.COLUMN_NAME_NAME + " text ," +
                    FeedEntry.COLUMN_NAME_DEPART + " text ," +
                    FeedEntry.COLUMN_NAME_PHONE + " text ," +
                    FeedEntry.COLUMN_NAME_EMAIL + " text " +" ) ";

    public static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "User";
        public static final String _ID = "_id";
        public static final String COLUMN_NAME_ID = "owner_id";
        public static final String COLUMN_NAME_PASS = "pass";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DEPART = "depart";
        public static final String COLUMN_NAME_PHONE = "phone";
        public static final String COLUMN_NAME_EMAIL = "email";
    }
}
