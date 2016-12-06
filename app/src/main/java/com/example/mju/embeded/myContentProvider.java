package com.example.mju.embeded;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class myContentProvider extends ContentProvider {

    private static final int POST = 1;
    private static final int POST_ID = 2;
    private static final int LOGIN = 3;
    private static final int LOGIN_ID = 4;

    private static final String URI_Post =
            "content://com.example.mju.embeded.myContentProvider/" + Post_Contract.FeedEntry.TABLE_NAME;
    private static final String URI_Login =
            "content://com.example.mju.embeded.myContentProvider/" + Login_Contract.FeedEntry.TABLE_NAME;
    public static final Uri CONTENT_URI_Post = Uri.parse(URI_Post);
    public static final Uri CONTENT_URI_Login = Uri.parse(URI_Login);
    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.mju.embeded.myContentProvider",
                Post_Contract.FeedEntry.TABLE_NAME, 1);
        uriMatcher.addURI("com.example.mju.embeded.myContentProvider",
                Post_Contract.FeedEntry.TABLE_NAME + "/#", 2);
        uriMatcher.addURI("com.example.mju.embeded.myContentProvider",
                Login_Contract.FeedEntry.TABLE_NAME, 3);
        uriMatcher.addURI("com.example.mju.embeded.myContentProvider",
                Login_Contract.FeedEntry.TABLE_NAME + "/#", 4);
    }
    private SQLiteDatabase mDB = null;
    private myDBHelper mDBHelper = null;

    public myContentProvider() {
    }

    @Override
    public boolean onCreate() {
        mDBHelper = new myDBHelper(getContext());
        mDB = mDBHelper.getWritableDatabase();
        mDBHelper.onCreate(mDB);

        ContentValues v = new ContentValues();
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_ID, "admin");
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_PASS, "1234");
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_NAME, "오지훈");
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_DEPART, "컴퓨터공학과");
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_PHONE, "01050502438");
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_EMAIL, "demo@naver.com");
        mDB.insert(Login_Contract.FeedEntry.TABLE_NAME, null, v);

        v.put(Post_Contract.FeedEntry.COLUMN_NAME_OWNER_ID, "admin");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_POST_NAME, "제2회 A-CUBE 게임잼");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_IMG, "acube");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_PERIOD, "12월 2일 (금) 19시 00분 ~ 12월 4일 (일) 16시 00분");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_PLACE, "[안양창조경제융합센터] 경기 안양시 동안구 관양동 1744 3층 에이큐브");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_LIMIT, 30);
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_CURRENT, 28);
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_DESCRIPTION, "A-CUBE GAME JAM은 기획자, 프로그래머, 아티스트로 나누어져 각 직군들이 즉석해서 하나의 팀을 만들고 정해진 시간동안 게임을 개발해 보는 기술 시연의 장으로 색다른 아이디어를 현실화 시켜보고 싶었으나 시간과 장소에 대한 제약때문에 현실화 하지 못한 게임 개발자들이 참가하여 주어진 주제에 맞추어 게임개발을 하는 게임개발자들의 축제입니다.");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_POST_NUMBER, 1);
        this.insert(CONTENT_URI_Post, v);

        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri _uri = null;
        switch(uriMatcher.match(uri)) {
            case POST:
                long _ID1 = mDB.insert(Post_Contract.FeedEntry.TABLE_NAME, null, values);
                if (_ID1 > 0) {
                    _uri = ContentUris.withAppendedId(CONTENT_URI_Post, _ID1);
                    getContext().getContentResolver().notifyChange(_uri, null);
                }
                break;
            case POST_ID:
                break;
            case LOGIN:
                long _ID2 = mDB.insert(Login_Contract.FeedEntry.TABLE_NAME, null, values);
                if (_ID2 > 0) {
                    _uri = ContentUris.withAppendedId(CONTENT_URI_Post, _ID2);
                    getContext().getContentResolver().notifyChange(_uri, null);
                }
                break;
            case LOGIN_ID:
                break;
            default:
                throw new SQLException("Failed to insert row into " + uri);
        }
        return _uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int n;
        switch(uriMatcher.match(uri)) {
            case POST :
                n = mDB.delete(Post_Contract.FeedEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case LOGIN :
                n = mDB.delete(Login_Contract.FeedEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default :
                throw new SQLException("Failed to insert row into " + uri);
        }
        return n;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb;
        String orderBy;
        Cursor c = null;
        switch(uriMatcher.match(uri)) {
            case POST :
                qb = new SQLiteQueryBuilder();
                qb.setTables(Post_Contract.FeedEntry.TABLE_NAME);
                if (TextUtils.isEmpty(sortOrder)) orderBy = "_id";
                else orderBy = sortOrder;

                c = qb.query(mDB, projection, selection, selectionArgs, null, null, orderBy);
                c.setNotificationUri(getContext().getContentResolver(), uri);
                break;
            case LOGIN :
                qb = new SQLiteQueryBuilder();
                qb.setTables(Login_Contract.FeedEntry.TABLE_NAME);
                if (TextUtils.isEmpty(sortOrder)) orderBy = "_id";
                else orderBy = sortOrder;

                c = qb.query(mDB, projection, selection, selectionArgs, null, null, orderBy);
                c.setNotificationUri(getContext().getContentResolver(),uri);
                break;
            default :
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        return c;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
