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

    private int post_number = 1;

    private static final int POST = 1;
    private static final int POST_ID = 2;
    private static final int LOGIN = 3;
    private static final int LOGIN_ID = 4;
    private static final int APPLY = 5;

    private static final String URI_Post =
            "content://com.example.mju.embeded.myContentProvider/" + Post_Contract.FeedEntry.TABLE_NAME;
    private static final String URI_Login =
            "content://com.example.mju.embeded.myContentProvider/" + Login_Contract.FeedEntry.TABLE_NAME;
    private static final String URI_Apply =
            "content://com.example.mju.embeded.myContentProvider/" + Apply_Contract.FeedEntry.TABLE_NAME;
    public static final Uri CONTENT_URI_Post = Uri.parse(URI_Post);
    public static final Uri CONTENT_URI_Login = Uri.parse(URI_Login);
    public static final Uri CONTENT_URI_Apply = Uri.parse(URI_Apply);
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
        uriMatcher.addURI("com.example.mju.embeded.myContentProvider",
                Apply_Contract.FeedEntry.TABLE_NAME, 5);
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

        // test data
        ContentValues v = new ContentValues();
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_ID, "admin");
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_PASS, "1234");
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_NAME, "오지훈");
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_DEPART, "컴퓨터공학과");
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_PHONE, "01050502438");
        v.put(Login_Contract.FeedEntry.COLUMN_NAME_EMAIL, "demo@naver.com");
        this.insert(CONTENT_URI_Login, v);

        ContentValues w = new ContentValues();
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_OWNER_ID, "admin");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_POST_NAME, "제2회 A-CUBE 게임잼");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_IMG, "acube");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_PERIOD, "12월 2일 (금) 19시 00분 ~ 12월 4일 (일) 16시 00분");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_PLACE, "[안양창조경제융합센터] 경기 안양시 동안구 관양동 1744 3층 에이큐브");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_LIMIT, 60);
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_CURRENT, 59);
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_DESCRIPTION, "A-CUBE GAME JAM은 기획자, 프로그래머, 아티스트로 나누어져 각 직군들이 즉석해서 하나의 팀을 만들고 정해진 시간동안 게임을 개발해 보는 기술 시연의 장으로 색다른 아이디어를 현실화 시켜보고 싶었으나 시간과 장소에 대한 제약때문에 현실화 하지 못한 게임 개발자들이 참가하여 주어진 주제에 맞추어 게임개발을 하는 게임개발자들의 축제입니다.");
//        w.put(Post_Contract.FeedEntry.COLUMN_NAME_POST_NUMBER, 1);
//            System.out.println("Initial insert = 1");
        this.insert(CONTENT_URI_Post, w);

        int i = 1;
        int j = 1;
        while(i-- > 0) {
            ContentValues u = new ContentValues();
            u.put(Apply_Contract.FeedEntry.COLUMN_NAME_POST_NUMBER, j);
            u.put(Apply_Contract.FeedEntry.COLUMN_NAME_USERNAME, "test");
            u.put(Apply_Contract.FeedEntry.COLUMN_NAME_POST_NUMBER, i);
            u.put(Apply_Contract.FeedEntry.COLUMN_NAME_EMAIL, "test@test.t");
            this.insert(CONTENT_URI_Apply, u);
        }
        j++;

        w.put(Post_Contract.FeedEntry.COLUMN_NAME_OWNER_ID, "admin");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_POST_NAME, "G-NEXT GAMEJAM");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_IMG, "gnext");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_PERIOD, "12월 9일 (금) 19시 00분 ~ 12월 11일 (일) 18시 00분");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_PLACE, "[경기창조경제혁신센터] 경기 성남시 분당구 삼평동 경기창조경제혁신센터 지하 2층 국제회의장");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_LIMIT, 90);
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_CURRENT, 55);
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_DESCRIPTION, "게임 개발자들과 함께 올해를 마무리할 G-NEXT GAMEJAM !\n" +
                "더 이상 재미없는 해커톤은 그만, 더 알차게 준비한 이런 내맘 모르고 너무해ᕙ(•̀‸•́‶)ᕗ\n" +
                "\n" +
                "48시간의 릴레이, 즐거운 게임잼과 함께하세요!\n" +
                "참가시 등록한 보증금은 현장에서 환급하여 드립니다.!! \n" +
                "\n" +
                "G-NEXT GAMEJAM은 인디 게임 개발사들과 함께합니다.");
//        w.put(Post_Contract.FeedEntry.COLUMN_NAME_POST_NUMBER, 2);
//            System.out.println("Initial insert = 2");
        this.insert(CONTENT_URI_Post, w);

        i = 35;
        while(i-- > 0) {
            ContentValues u = new ContentValues();
            u.put(Apply_Contract.FeedEntry.COLUMN_NAME_POST_NUMBER, j);
            u.put(Apply_Contract.FeedEntry.COLUMN_NAME_USERNAME, "test");
            u.put(Apply_Contract.FeedEntry.COLUMN_NAME_POST_NUMBER, i);
            u.put(Apply_Contract.FeedEntry.COLUMN_NAME_EMAIL, "test@test.t");
            this.insert(CONTENT_URI_Apply, u);
        }
        j++;

        w.put(Post_Contract.FeedEntry.COLUMN_NAME_OWNER_ID, "admin");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_POST_NAME, "대한민국 게임잼 2016");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_IMG, "korea");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_PERIOD, "12월 9일 (금) 19시 00분 ~ 12월 11일 (일) 18시 00분");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_PLACE, "[aT센터] 서울 서초구 양재동");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_LIMIT, 120);
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_CURRENT, 112);
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_DESCRIPTION, "48시간 동안의 게임을 만드는 축제. 대한민국 게임잼 2016!\n" +
                "12. 9. - 12.11. 서울 양재 aT센터에서 개최됩니다. \n" +
                "총 300만원 상당의 개발지원금과, 다양한 게임개발 관련 이벤트 경품(기계식 키보드 등)이 제공됩니다.\n" +
                "\n" +
                "게임잼 신청자에겐 무료로 \n" +
                "11/26(토) \"개발자를 위한 도트 디자인 입문 - 도트 클리커 게임 만들기\" http://onoffmix.com/event/84246\n" +
                "12/03(토) \"슈팅 게임 제작을 통한 Unity3d의 기본기능 익히기\" http://onoffmix.com/event/84245\n" +
                "과정에 참여할 수 있는 기회를 제공해 드립니다.\n");
//        w.put(Post_Contract.FeedEntry.COLUMN_NAME_POST_NUMBER, 3);
//            System.out.println("Initial insert = 3");
        this.insert(CONTENT_URI_Post, w);

        i = 8;
        while(i-- > 0) {
            ContentValues u = new ContentValues();
            u.put(Apply_Contract.FeedEntry.COLUMN_NAME_POST_NUMBER, j);
            u.put(Apply_Contract.FeedEntry.COLUMN_NAME_USERNAME, "test");
            u.put(Apply_Contract.FeedEntry.COLUMN_NAME_POST_NUMBER, i);
            u.put(Apply_Contract.FeedEntry.COLUMN_NAME_EMAIL, "test@test.t");
            this.insert(CONTENT_URI_Apply, u);
        }
        j++;

        w.put(Post_Contract.FeedEntry.COLUMN_NAME_OWNER_ID, "admin");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_POST_NAME, "시험 끝나고 영화보러 갈");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_IMG, "movie");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_PERIOD, "12월 19일 (월) 19시 00분 ~ 12월 20일 (화) 19시 00분");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_PLACE, "용인 롯데시네마");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_LIMIT, 4);
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_CURRENT, 2);
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_DESCRIPTION, "한학기 시험보시느라 다들 고생 많으셨죠!?\n" +
                "영화는 보고싶은데 친구들은 다 봤고... \n" +
                "같이 영화보러 가요~!\n");
        this.insert(CONTENT_URI_Post, w);

        i = 2;
        while(i-- > 0) {
            ContentValues u = new ContentValues();
            u.put(Apply_Contract.FeedEntry.COLUMN_NAME_POST_NUMBER, j);
            u.put(Apply_Contract.FeedEntry.COLUMN_NAME_USERNAME, "test");
            u.put(Apply_Contract.FeedEntry.COLUMN_NAME_POST_NUMBER, i);
            u.put(Apply_Contract.FeedEntry.COLUMN_NAME_EMAIL, "test@test.t");
            this.insert(CONTENT_URI_Apply, u);
        }
        j++;

        w.put(Post_Contract.FeedEntry.COLUMN_NAME_OWNER_ID, "admin");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_POST_NAME, "오버워치 한판!?");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_IMG, "game");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_PERIOD, "12월 20일 (화) 18시 00분 ~ 12월 20일 (화) 23시 59분");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_PLACE, "넘버원 피시방");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_LIMIT, 4);
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_CURRENT, 2);
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_DESCRIPTION, "오버워치 한판 하러가실 분!?\n" +
                "시즌3인데 설마... 아직도 실버에요?\n" +
                "빨리 다이아 올리러 갑시다.\n");
        this.insert(CONTENT_URI_Post, w);

        i = 2;
        while(i-- > 0) {
            ContentValues u = new ContentValues();
            u.put(Apply_Contract.FeedEntry.COLUMN_NAME_POST_NUMBER, j);
            u.put(Apply_Contract.FeedEntry.COLUMN_NAME_USERNAME, "test");
            u.put(Apply_Contract.FeedEntry.COLUMN_NAME_POST_NUMBER, i);
            u.put(Apply_Contract.FeedEntry.COLUMN_NAME_EMAIL, "test@test.t");
            this.insert(CONTENT_URI_Apply, u);
        }
        j++;

        w.put(Post_Contract.FeedEntry.COLUMN_NAME_OWNER_ID, "admin");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_POST_NAME, "맛집탐방하러 가실분!");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_IMG, "food");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_PERIOD, "12월 21일 (수) 18시 00분 ~ 12월 21일 (수) 20시 00분");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_PLACE, "효자동 초밥집");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_LIMIT, 4);
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_CURRENT, 2);
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_DESCRIPTION, "목표는 하나!\n" +
                "효자동 초밥집으로 GO GO\n");
        this.insert(CONTENT_URI_Post, w);

        i = 2;
        while(i-- > 0) {
            ContentValues u = new ContentValues();
            u.put(Apply_Contract.FeedEntry.COLUMN_NAME_POST_NUMBER, j);
            u.put(Apply_Contract.FeedEntry.COLUMN_NAME_USERNAME, "test");
            u.put(Apply_Contract.FeedEntry.COLUMN_NAME_POST_NUMBER, i);
            u.put(Apply_Contract.FeedEntry.COLUMN_NAME_EMAIL, "test@test.t");
            this.insert(CONTENT_URI_Apply, u);
        }
        j++;

        w.put(Post_Contract.FeedEntry.COLUMN_NAME_OWNER_ID, "admin");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_POST_NAME, "몸보신하러 가실분");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_IMG, "solo");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_PERIOD, "12월 22일 (목) 18시 00분 ~ 12월 22일 (목) 20시 00분");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_PLACE, "경복궁 토속촌");
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_LIMIT, 4);
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_CURRENT, 2);
        w.put(Post_Contract.FeedEntry.COLUMN_NAME_DESCRIPTION, "시험도 끝났는데.. 몸보신하러 갑시다\n" +
                "보양식은 삼계탕으로!\n");
        this.insert(CONTENT_URI_Post, w);

        i = j;
        while(i-- > 0) {
            ContentValues u = new ContentValues();
            u.put(Apply_Contract.FeedEntry.COLUMN_NAME_POST_NUMBER, j);
            u.put(Apply_Contract.FeedEntry.COLUMN_NAME_USERNAME, "test");
            u.put(Apply_Contract.FeedEntry.COLUMN_NAME_POST_NUMBER, i);
            u.put(Apply_Contract.FeedEntry.COLUMN_NAME_EMAIL, "test@test.t");
            this.insert(CONTENT_URI_Apply, u);
        }
        j++;

        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri _uri = null;
        long _ID;
        switch(uriMatcher.match(uri)) {
            case POST:
                values.put(Post_Contract.FeedEntry.COLUMN_NAME_POST_NUMBER, post_number++);
                _ID = mDB.insert(Post_Contract.FeedEntry.TABLE_NAME, null, values);
                if (_ID > 0) {
                    _uri = ContentUris.withAppendedId(CONTENT_URI_Post, _ID);
                    getContext().getContentResolver().notifyChange(_uri, null);
                }
                break;
            case POST_ID:
                break;
            case LOGIN:
                _ID = mDB.insert(Login_Contract.FeedEntry.TABLE_NAME, null, values);
                if (_ID > 0) {
                    _uri = ContentUris.withAppendedId(CONTENT_URI_Login, _ID);
                    getContext().getContentResolver().notifyChange(_uri, null);
                }
                break;
            case LOGIN_ID:
                break;
            case APPLY:
                _ID = mDB.insert(Apply_Contract.FeedEntry.TABLE_NAME, null, values);
                if (_ID > 0) {
                    _uri = ContentUris.withAppendedId(CONTENT_URI_Apply, _ID);
                    getContext().getContentResolver().notifyChange(_uri, null);
                }
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
            case APPLY :
                n = mDB.delete(Apply_Contract.FeedEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default :
                throw new SQLException("Failed to delete row into " + uri);
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
            case APPLY :
                qb = new SQLiteQueryBuilder();
                qb.setTables(Apply_Contract.FeedEntry.TABLE_NAME);
                if (TextUtils.isEmpty(sortOrder)) orderBy = Apply_Contract.FeedEntry.COLUMN_NAME_POST_NUMBER;
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
        int n;
        switch(uriMatcher.match(uri)) {
            case POST :
                n = mDB.update(Post_Contract.FeedEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case LOGIN :
                n = mDB.update(Login_Contract.FeedEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case APPLY :
                n = mDB.update(Apply_Contract.FeedEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new SQLException("Failed to update row into " + uri);
        }
        return n;
    }
}
