package com.example.mju.embeded;

import android.provider.BaseColumns;

public final class Login_Contract {

    public Login_Contract(){};

    public static final String DATABASE_NAME = "login_DB";
    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " ( " +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_ID + " text , " +
                    FeedEntry.COLUMN_NAME_PASS + " text " +
                    FeedEntry.COLUMN_NAME_PHONE + " text " +
                    FeedEntry.COLUMN_NAME_EMAIL + " text " +
                    FeedEntry.COLUMN_NAME_DEPART + " text " +" ) ";

    public static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "User";
        public static final String _ID = "_id";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_PASS = "pass";
        public static final String COLUMN_NAME_PHONE = "phone";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_DEPART = "depart";
    }
}
