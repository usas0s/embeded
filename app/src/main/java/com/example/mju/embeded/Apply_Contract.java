package com.example.mju.embeded;

import android.provider.BaseColumns;

/**
 * Created by Only user one on 2016-11-29.
 */

public class Apply_Contract {

    public Apply_Contract(){};

    public static final String DATABASE_NAME = "DB";
    public static final String SQL_PRAGMA_ON = "PRAGMA foreign_keys = ON";
    public static final String SQL_PRAGMA_OFF = "PRAGMA foreign_keys = OFF";
    public static final String SQL_CREATE_TABLE =
            //            "CREATE TABLE " + FeedEntry.TABLE_NAME + " IF NOT EXISTS ( " +
            "CREATE TABLE IF NOT EXISTS " + FeedEntry.TABLE_NAME + " ( " +
//                    FeedEntry._ID + " INTEGER PRIMARY KEY , " +
                    FeedEntry.COLUMN_NAME_POST_NUMBER + " INTEGER , " +
                    FeedEntry.COLUMN_NAME_USERNAME + " text , " +
                    FeedEntry.COLUMN_NAME_CALLNUMBER + " integer ," +
                    FeedEntry.COLUMN_NAME_EMAIL + " text " +
                    ", PRIMARY KEY ( " +
                    FeedEntry.COLUMN_NAME_POST_NUMBER + " , " +
                    FeedEntry.COLUMN_NAME_USERNAME + " , " +
                    FeedEntry.COLUMN_NAME_CALLNUMBER + " , " +
                    FeedEntry.COLUMN_NAME_EMAIL +
                    " ) ON CONFLICT FAIL " +
                    " ) WITHOUT ROWID ";

    public static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "apply_table";
//        public static final String _ID = "_id";
        // 글번호
        public static final String COLUMN_NAME_POST_NUMBER = "post_number";
        // 이름
        public static final String COLUMN_NAME_USERNAME = "name";
        // 전화번호
        public static final String COLUMN_NAME_CALLNUMBER = "call_number";
        // e-mail
        public static final String COLUMN_NAME_EMAIL = "email";
    }
}

