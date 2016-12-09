package com.example.mju.embeded;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
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

public class Details extends AppCompatActivity {
    // 멤버 변수들
    private SQLiteDatabase mDB;
    private myDBHelper mDbHelper;
    private ArrayList<HashMap<String, Object>> list; // Hashmap 을 저장하는 list
    private HashMap<String, Object> hashMap; // query 를 위한 hashmap
    private String path; // 이미지 파일의 이름
    private String name;
    private int target; // 검색 할 글의 번호
    private String Lat,Lng; // 위도, 경도
    private String destination; // 목적지 이름. 지도에서도 사용.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        target = intent.getIntExtra("number",1);
        System.out.println("target from intent = " + target);

        // DB 연동
        mDbHelper = new myDBHelper(this);
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
                                intent.putExtra("img",path);
                                intent.putExtra("name",name);
                                intent.putExtra("number",target);
                                startActivity(intent);
                            }
                        });
                View snackView = snackbar.getView();
                snackView.setBackgroundColor(Color.parseColor("#3498DB"));
                snackbar.show();
            }
        });



        // DB 검색 후 결과 저장
        list = selectList(target); // 모임 번호. 1~n
        System.out.println("★ list_empty = " + list.isEmpty() + " size = " + list.size());
        hashMap = list.get(0);

        // 모임명 셋팅
        name = hashMap.get("post_name").toString();
        setTitle(name);

        // 썸네일 셋팅
        ImageView iv_thumbnail = (ImageView)findViewById(R.id.detail_thumbnail);
        path = hashMap.get("img_path").toString(); // 모임명에 숫자를 붙여 이미지 파일을 선택. 0 = 표지, 1~n = 상세 설명
        System.out.println("★★image file name = " + path+"0");
        iv_thumbnail.setImageResource(getResources().getIdentifier(path+"0","drawable",getPackageName()));

        // 개설자 ID 셋팅
        TextView tv_id = (TextView) findViewById(R.id.detail_id);
        tv_id.setText(hashMap.get("owner_id").toString());

        // 모임 기간 셋팅
        TextView tv_period = (TextView) findViewById(R.id.detail_period);
        tv_period.setText(hashMap.get("period").toString());

        // 모임 장소 셋팅
        TextView tv_place = (TextView) findViewById(R.id.detail_place);
        destination = hashMap.get("place").toString();
        tv_place.setText(destination);

        // 최대 | 현재 인원 셋팅
        TextView tv_limit = (TextView) findViewById(R.id.detail_limit);
        tv_limit.setText("총 " + hashMap.get("limitation").toString() + "명 | " + hashMap.get("present").toString() + "명 신청 가능");

        // 상세 설명 셋팅
        TextView tv_description = (TextView) findViewById(R.id.detail_description);
        tv_description.setText(hashMap.get("description").toString());

        // 설명 이미지 셋팅
        ImageView iv_image = (ImageView)findViewById(R.id.detail_image);
        path = hashMap.get("img_path").toString(); // 모임명에 숫자를 붙여 이미지 파일을 선택. 0 = 표지, 1~n = 상세 설명
        System.out.println("★★detail image file name = " + path+"1");
        iv_image.setImageResource(getResources().getIdentifier(path+"1","drawable",getPackageName()));

        // LatLng
        Lat = hashMap.get("latitude").toString();
        Lng = hashMap.get("longitude").toString();
    }

    // 지도 호출
    public void showMap(View view)
    {
        Uri uri = Uri.parse("geo:"+Lat+","+Lng+"?q="+Lat+","+Lng+"("+destination+")");
        Intent it = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(it);
    }

    // SNS 공유하기
    public void share(View view)
    {
        String share_postname = hashMap.get("post_name").toString(); // 모임 이름
        String share_description = hashMap.get("description").toString(); // 상세 설명
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_SUBJECT, share_postname);
        intent.putExtra(Intent.EXTRA_TEXT, "\n\n" + share_description + "\n\n같이 가지 않으실래요?"); // [모임명] 상세설명" 메시지로 보낸다.

        intent.setType("text/plain");

        startActivity(Intent.createChooser(intent, "공유하기"));
    }

    // DB 탐색 기능. parameter = 찾을 int형 post_number
    public ArrayList<HashMap<String, Object>> selectList(int target_PostNumber) {
        System.out.println("★target_PostNumber = " + target_PostNumber);
        HashMap<String, Object> map;
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        String[] column = {"owner_id", "post_name", "img_path", "period", "place", "limitation", "present", "description", "post_number", "latitude", "longitude"};
        Cursor cursor = null;

        try {
            cursor = mDB.query("post_table", column, null, null, null, null, null);
            System.out.println("★cursor.getCount() = " + cursor.getCount());
            System.out.println("★initial cursor location = " + cursor.getPosition());
            cursor.moveToFirst();
            System.out.println("★first cursor location = " + cursor.getPosition());

            // 삭제 할지 좀 더 디버깅 해보기. 못 찾으면 마지막 항목 나오는 버그 있음.
            while((!(cursor.getString(8).equals(String.valueOf(target_PostNumber)))) && !(cursor.isLast())) // 일치하는 post_number 를 탐색. 커서가 끝에 도달하면 그만.
            {
                cursor.moveToNext(); // 끝에서 한번 더 가게되면 에러날수도. 에러 처리 바로 위에 임시로 넣음.
                System.out.println("★moving cursor = " + cursor.getPosition());
            }

            {
                System.out.println("★after moving cursor location = " + cursor.getPosition());
                map = new HashMap<String, Object>();
                System.out.println("★created HashMap");

                // 모든 column의 정보(column's name & value)를 map에 저장. 쉽게 말해 tuple 통째로 저장.
                for(int index = 0 ; index < column.length; index++)
                {
                    map.put(column[index],cursor.getString(index));
                    System.out.println("★found " + column[index] + " = " + cursor.getString(index));
                }

                System.out.println("★map_empty = " + map.isEmpty());
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
