package com.example.mju.embeded;

import android.provider.BaseColumns;

public final class Post_Contract {

    public Post_Contract(){};

    public static final String DATABASE_NAME = "Post_DB";
    public static final String SQL_PRAGMA_ON = "PRAGMA foreign_keys = ON";
    public static final String SQL_PRAGMA_OFF = "PRAGMA foreign_keys = OFF";
    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " ( " +
                    FeedEntry.COLUMN_NAME_OWNER_ID + " text ," +
                    FeedEntry.COLUMN_NAME_POST_NAME + " text ," +
                    FeedEntry.COLUMN_NAME_IMG + " text ," +
                    FeedEntry.COLUMN_NAME_PERIOD + " text ," +
                    FeedEntry.COLUMN_NAME_PLACE + " text ," +
                    FeedEntry.COLUMN_NAME_LIMIT + " INTEGER ," +
                    FeedEntry.COLUMN_NAME_CURRENT + " INTEGER ," +
                    FeedEntry.COLUMN_NAME_DESCRIPTION + " text ," +
                    FeedEntry.COLUMN_NAME_POST_NUMBER + " INTEGER PRIMARY KEY ," +
                    " FOREIGN KEY(writer_id) REFERENCES User(id)" + " ) ";

    public static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "post_table";
        public static final String _ID = "_id";
        // 작성자 ID
        public static final String COLUMN_NAME_OWNER_ID = "owner_id";
        // 모임 이름
        public static final String COLUMN_NAME_POST_NAME = "post_name";
        // IMG Link
        public static final String COLUMN_NAME_IMG = "img_path";
        // 모집기간
        public static final String COLUMN_NAME_PERIOD = "period";
        // 모집장소
        public static final String COLUMN_NAME_PLACE = "place";
        // 최대 신청인원
        public static final String COLUMN_NAME_LIMIT = "limit";
        // 현재 신청인원
        public static final String COLUMN_NAME_CURRENT = "current";
        // 모임설명
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        // 글번호 (PK)
        public static final String COLUMN_NAME_POST_NUMBER = "post_number";
    }
}
