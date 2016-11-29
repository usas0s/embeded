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

        // DB 검색 후 결과 저장
        ArrayList<HashMap<String, Object>> list = selectList();
        HashMap<String, Object> hashMap = list.get(0);

        // 모임명 셋팅
        setTitle(hashMap.get("post_name").toString());

        // 썸네일 셋팅
        ImageView iv_thumbnail = (ImageView)findViewById(R.id.detail_thumbnail);
        String path = hashMap.get("img_path").toString()+"0";
        System.out.println("★★image file name = "+path);
//        int resID = getResources().getIdentifier(hashMap.get("img_path").toString(),"drawable",getPackageName());
        iv_thumbnail.setImageResource(getResources().getIdentifier(path,"drawable",getPackageName()));

        // 모임 기간 셋팅
        TextView tv_period = (TextView) findViewById(R.id.detail_period);
        System.out.println("★ list_empty = " + list.isEmpty() + " size = " + list.size());
        tv_period.setText(hashMap.get("period").toString());

        // 모임 장소 셋팅
        TextView tv_place = (TextView) findViewById(R.id.detail_place);
//        tv_place.setText("DB.place");
        tv_place.setText(hashMap.get("place").toString());

        // 최대 인원 셋팅
        TextView tv_limit = (TextView) findViewById(R.id.detail_limit);
        tv_limit.setText("총 " + hashMap.get("limitation").toString() + "명 | ");

        // 현재 인원 셋팅
        TextView tv_current = (TextView) findViewById(R.id.detail_current);
        tv_current.setText(hashMap.get("present").toString() + "명 신청 가능");

        // 상세 설명 셋팅
        TextView tv_description = (TextView) findViewById(R.id.detail_description);
//        tv_description.setText("DB.description");
        tv_description.setText(hashMap.get("description").toString());

        // 설명 이미지 셋팅
        ImageView iv_image = (ImageView)findViewById(R.id.detail_image);
        path = hashMap.get("img_path").toString()+"1";
        System.out.println("★★image file name 2 = "+path);
        iv_image.setImageResource(getResources().getIdentifier(path,"drawable",getPackageName()));
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
        String[] column = {"owner_id", "post_name", "img_path", "period", "place", "limitation", "present", "description", "post_number"};
        Cursor cursor = null;
        try {
            cursor = mDB.query("post_table", column, null, null, null, null, null);
            System.out.println("★cursor getCount = " + cursor.getCount());
            System.out.println("★position 1 = " + cursor.getPosition());
            cursor.moveToFirst();
            System.out.println("★position 2 = " + cursor.getPosition());
//            while (cursor.moveToNext())
            {
                System.out.println("★position 3 = " + cursor.getPosition());
                map = new HashMap<String, Object>();
                System.out.println("★hash");

                for(int index = 0 ; index < column.length; index++)
                {
                    map.put(column[index],cursor.getString(index));
                    System.out.println("★found " + column[index] + " = " + cursor.getString(index));
                }
//                map.put("owner_id", cursor.getString(0));
//                System.out.println("★found id = " + cursor.getString(0));
//
//                map.put("post_name", cursor.getString(1));
//                System.out.println("★found name = " + cursor.getString(1));
//
//                map.put("img_path", cursor.getString(2));
//                System.out.println("★found path = " + cursor.getString(2));

                System.out.println("★map_empty= " + map.isEmpty());
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
