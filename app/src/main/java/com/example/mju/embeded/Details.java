package com.example.mju.embeded;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Details extends AppCompatActivity {
    private SQLiteDatabase mDB;
    Post_DbHelper mDbHelper;
    Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // DB 연동
        mDbHelper = new Post_DbHelper(this);
        mDB = mDbHelper.getWritableDatabase();
//        mDbHelper.onCreate(mDB);

        // FAB
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            // floating 버튼 터치 시 참가 신청 페이지 열림.
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "참가 신청하기", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Snackbar snackbar;
                snackbar = Snackbar.make(view, "참가 신청하기", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener()
                        {
                            // 참가 신청 버튼 터치 시 할일
                            @Override
                            public void onClick(View view)
                            {
                                Toast.makeText(getApplicationContext(),"참가 신청 페이지로 이동합니다.",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(),Apply.class);
                                startActivity(intent);
                            }
                        });
                View snackView = snackbar.getView();
                snackView.setBackgroundColor(Color.parseColor("#3498DB"));
                snackbar.show();
            }
        });

        // 썸네일 셋팅
        String path = "acube0";
        ImageView iv_thumbnail = (ImageView)findViewById(R.id.detail_thumbnail);
        int resID = getResources().getIdentifier(path,"drawable",getPackageName());
        iv_thumbnail.setImageResource(resID);
//        -> 한줄로 수정하면 iv_thumbnail.setImageResource(getResources().getIdentifier(DB.image_path,"drawable",getPackageName()));

//        iv_thumbnail.setImageResource(R.drawable.acube0); // DB's image_path 받는걸로 수정

        // 모임 기간 셋팅
//        String temp[] = {"img_path"};
//        mCursor = mDB.query("post_table",temp,null,null,null,null,null);
//        mCursor.moveToFirst();
//        String period = mCursor.getString(0);
//        ArrayList<HashMap<String, Object>> list = selectList();
        TextView tv_period = (TextView) findViewById(R.id.detail_period);
        tv_period.setText("기간");
//        tv_period.setText(list.get(0).toString());

        // 모임 장소 셋팅
        TextView tv_place = (TextView) findViewById(R.id.detail_place);
        tv_place.setText("DB.place");

        // 최대 인원 셋팅
        TextView tv_limit = (TextView) findViewById(R.id.detail_limit);
        tv_limit.setText("총 DB.limit명 | ");

        // 현재 인원 셋팅
        TextView tv_current = (TextView) findViewById(R.id.detail_current);
        tv_current.setText("DB.current명 신청 가능");

        // 상세 설명 셋팅
        TextView tv_description = (TextView) findViewById(R.id.detail_description);
        tv_description.setText("DB.description");

        // 설명 이미지 셋팅
        ImageView iv_image = (ImageView)findViewById(R.id.detail_image);
//        String path = DB.image_path;
        String path2 = "in";
        int resID2 = getResources().getIdentifier(path2,"drawable",getPackageName());
        iv_image.setImageResource(resID2);
//        -> 한줄로 수정하면 iv_thumbnail.setImageResource(getResources().getIdentifier(DB.image_path,"drawable",getPackageName()));
    }

    public void showMap(View view)
    {
        Intent intent = new Intent(this,Maps.class);
        startActivity(intent);
    }
//
//    private void sendShare() {
//        Intent intent = new Intent(Intent.ACTION_SEND);
//        intent.setType("image/*");
//
//        List<ResolveInfo> resInfo = getPackageManager().queryIntentActivities(intent, 0);
//        if (resInfo.isEmpty()) {
//            return;
//        }
//
//        List<Intent> shareIntentList = new ArrayList<Intent>();
//
//        for (ResolveInfo info : resInfo) {
//            Intent shareIntent = (Intent) intent.clone();
//
//            if (info.activityInfo.packageName.toLowerCase().equals("com.facebook.katana")) {
//                //facebook
//                shareIntent.setType("text/plain");
//                shareIntent.putExtra(Intent.EXTRA_TEXT, "http://www.google.com");
////                shareIntent.setType("image/jpg");
////                shareIntent.putExtra(Intent.EXTRA_STREAM,  Uri.parse("file:///"+mImagePath));
//            } else {
//                shareIntent.setType("image/*");
//                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "제목");
//                shareIntent.putExtra(Intent.EXTRA_TEXT, "구글 http://www.google.com #");
//                shareIntent.putExtra(Intent.EXTRA_STREAM,  Uri.parse("file:///"));
//            }
//            shareIntent.setPackage(info.activityInfo.packageName);
//            shareIntentList.add(shareIntent);
//        }
//
//        Intent chooserIntent = Intent.createChooser(shareIntentList.remove(0), "select");
//        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, shareIntentList.toArray(new Parcelable[]{}));
//        startActivity(chooserIntent);
//    }

    public ArrayList<HashMap<String, Object>> selectList() {
        HashMap<String, Object> map;
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        String[] column = {"owner_id", "post_name", "img_path"};
        Cursor cursor = null;
        try {
            cursor = mDB.query("post_table", column, null, null, null, null, null);
            while (cursor.moveToNext()) {
                map = new HashMap<String, Object>();
                map.put("owner_id", cursor.getString(0));
                map.put("post_name", cursor.getString(1));
                map.put("img_path", cursor.getString(2));
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
        }
        return list;
    }
}
