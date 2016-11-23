package com.example.mju.embeded;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;


public class Login_ContentProvider extends ContentProvider {
    private static final String URI = "content://com.example.mju.embeded/login_DB";
    public static final Uri CONTENT_URI = Uri.parse(URI);
    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.mju.embeded", "login_DB", 1);
    }
    private SQLiteDatabase mDB = null;

    public Login_ContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return mDB.delete(Login_Contract.FeedEntry.TABLE_NAME, selection, null);
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        mDB.insert(Login_Contract.FeedEntry.TABLE_NAME, null, values);
        return CONTENT_URI;
    }

    @Override
    public boolean onCreate() {
        Login_DbHelper loginDB = new Login_DbHelper(getContext());
        mDB = loginDB.getWritableDatabase();
        loginDB.onCreate(mDB);

        /*
        ContentValues v = new ContentValues();
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_ID, "admin");
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_PASS, "1234");
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_NAME, "오지훈");
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_DEPART, "컴퓨터공학과");
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_PHONE, "01050502438");
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_EMAIL, "demo@naver.com");
        mDB.insert(Login_Contract.FeedEntry.TABLE_NAME, null, v);

        v.put(Login_Contract.FeedEntry.COLUMN_NAME_ID, "admin2");
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_PASS, "1234");
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_NAME, "오즈");
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_DEPART, "컴퓨터공학과");
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_PHONE, "01050502438");
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_EMAIL, "demo@naver.com");
        mDB.insert(Login_Contract.FeedEntry.TABLE_NAME, null, v);
        */

        return (loginDB==null)? false: true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(Login_Contract.FeedEntry.TABLE_NAME);
        String orderBy;
        if (TextUtils.isEmpty(sortOrder)) orderBy = "_id";
        else orderBy = sortOrder;

        Cursor c = qb.query(mDB, projection, selection, selectionArgs, null, null, orderBy);
        c.setNotificationUri(getContext().getContentResolver(),uri);

        return c;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
