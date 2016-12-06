//Created at 17 Oct 16 by MH K

package com.example.mju.embeded;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class SearchResult extends AppCompatActivity {
    //    private static final String URI = "content://com.example.mju.embeded/Post_DB";
    public static final Uri Content_URI = myContentProvider.CONTENT_URI_Post;

    ListView listview ;
    Search_ListViewAdapter adapter;
    ContentResolver cr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        // Adapter 생성
        adapter = new Search_ListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        // ContentResolver, DB 연동
        cr = getContentResolver();
        ContentValues v = new ContentValues();

        v.put(Post_Contract.FeedEntry.COLUMN_NAME_OWNER_ID, "admin");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_POST_NAME, "제2회 A-CUBE 게임잼");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_IMG, "acube");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_PERIOD, "12월 2일 (금) 19시 00분 ~ 12월 4일 (일) 16시 00분");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_PLACE, "[안양창조경제융합센터] 경기 안양시 동안구 관양동 1744 3층 에이큐브");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_LIMIT, 60);
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_CURRENT, 59);
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_DESCRIPTION, "A-CUBE GAME JAM은 기획자, 프로그래머, 아티스트로 나누어져 각 직군들이 즉석해서 하나의 팀을 만들고 정해진 시간동안 게임을 개발해 보는 기술 시연의 장으로 색다른 아이디어를 현실화 시켜보고 싶었으나 시간과 장소에 대한 제약때문에 현실화 하지 못한 게임 개발자들이 참가하여 주어진 주제에 맞추어 게임개발을 하는 게임개발자들의 축제입니다.");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_POST_NUMBER, 1);
        System.out.println("Initial insert = 1");
        cr.insert(Content_URI, v);

        v.put(Post_Contract.FeedEntry.COLUMN_NAME_OWNER_ID, "admin");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_POST_NAME, "G-NEXT GAMEJAM");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_IMG, "gnext");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_PERIOD, "12월 9일 (금) 19시 00분 ~ 12월 11일 (일) 18시 00분");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_PLACE, "[경기창조경제혁신센터] 경기 성남시 분당구 삼평동 경기창조경제혁신센터 지하 2층 국제회의장");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_LIMIT, 90);
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_CURRENT, 55);
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_DESCRIPTION, "게임 개발자들과 함께 올해를 마무리할 G-NEXT GAMEJAM !\n" +
                "더 이상 재미없는 해커톤은 그만, 더 알차게 준비한 이런 내맘 모르고 너무해ᕙ(•̀‸•́‶)ᕗ\n" +
                "\n" +
                "48시간의 릴레이, 즐거운 게임잼과 함께하세요!\n" +
                "참가시 등록한 보증금은 현장에서 환급하여 드립니다.!! \n" +
                "\n" +
                "G-NEXT GAMEJAM은 인디 게임 개발사들과 함께합니다.");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_POST_NUMBER, 2);
        System.out.println("Initial insert = 2");
        cr.insert(Content_URI, v);

        v.put(Post_Contract.FeedEntry.COLUMN_NAME_OWNER_ID, "admin");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_POST_NAME, "대한민국 게임잼 2016");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_IMG, "korea");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_PERIOD, "12월 9일 (금) 19시 00분 ~ 12월 11일 (일) 18시 00분");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_PLACE, "[aT센터] 서울 서초구 양재동");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_LIMIT, 120);
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_CURRENT, 112);
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_DESCRIPTION, "48시간 동안의 게임을 만드는 축제. 대한민국 게임잼 2016!\n" +
                "12. 9. - 12.11. 서울 양재 aT센터에서 개최됩니다. \n" +
                "총 300만원 상당의 개발지원금과, 다양한 게임개발 관련 이벤트 경품(기계식 키보드 등)이 제공됩니다.\n" +
                "\n" +
                "게임잼 신청자에겐 무료로 \n" +
                "11/26(토) \"개발자를 위한 도트 디자인 입문 - 도트 클리커 게임 만들기\" http://onoffmix.com/event/84246\n" +
                "12/03(토) \"슈팅 게임 제작을 통한 Unity3d의 기본기능 익히기\" http://onoffmix.com/event/84245\n" +
                "과정에 참여할 수 있는 기회를 제공해 드립니다.\n");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_POST_NUMBER, 3);
        System.out.println("Initial insert = 3");
        cr.insert(Content_URI, v);

        /*
        // 초기 데이터 추가
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_OWNER_ID, "admin");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_POST_NAME, "제2회 A-CUBE 게임잼");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_IMG, "acube");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_PERIOD, "12월 2일 (금) 19시 00분 ~ 12월 4일 (일) 16시 00분");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_PLACE, "[안양창조경제융합센터] 경기 안양시 동안구 관양동 1744 3층 에이큐브");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_LIMIT, 60);
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_CURRENT, 59);
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_DESCRIPTION, "A-CUBE GAME JAM은 기획자, 프로그래머, 아티스트로 나누어져 각 직군들이 즉석해서 하나의 팀을 만들고 정해진 시간동안 게임을 개발해 보는 기술 시연의 장으로 색다른 아이디어를 현실화 시켜보고 싶었으나 시간과 장소에 대한 제약때문에 현실화 하지 못한 게임 개발자들이 참가하여 주어진 주제에 맞추어 게임개발을 하는 게임개발자들의 축제입니다.");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_POST_NUMBER, 1);
        mDB.insert(Post_Contract.FeedEntry.TABLE_NAME, null, v);

        v.put(Post_Contract.FeedEntry.COLUMN_NAME_OWNER_ID, "admin");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_POST_NAME, "G-NEXT GAMEJAM");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_IMG, "gnext");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_PERIOD, "12월 9일 (금) 19시 00분 ~ 12월 11일 (일) 18시 00분");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_PLACE, "[경기창조경제혁신센터] 경기 성남시 분당구 삼평동 경기창조경제혁신센터 지하 2층 국제회의장");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_LIMIT, 90);
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_CURRENT, 55);
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_DESCRIPTION, "게임 개발자들과 함께 올해를 마무리할 G-NEXT GAMEJAM !\n" +
                "더 이상 재미없는 해커톤은 그만, 더 알차게 준비한 이런 내맘 모르고 너무해ᕙ(•̀‸•́‶)ᕗ\n" +
                "\n" +
                "48시간의 릴레이, 즐거운 게임잼과 함께하세요!\n" +
                "참가시 등록한 보증금은 현장에서 환급하여 드립니다.!! \n" +
                "\n" +
                "G-NEXT GAMEJAM은 인디 게임 개발사들과 함께합니다.");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_POST_NUMBER, 2);
        mDB.insert(Post_Contract.FeedEntry.TABLE_NAME, null, v);

        v.put(Post_Contract.FeedEntry.COLUMN_NAME_OWNER_ID, "admin");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_POST_NAME, "대한민국 게임잼 2016");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_IMG, "korea");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_PERIOD, "12월 9일 (금) 19시 00분 ~ 12월 11일 (일) 18시 00분");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_PLACE, "[aT센터] 서울 서초구 양재동");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_LIMIT, 120);
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_CURRENT, 112);
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_DESCRIPTION, "48시간 동안의 게임을 만드는 축제. 대한민국 게임잼 2016!\n" +
                "12. 9. - 12.11. 서울 양재 aT센터에서 개최됩니다. \n" +
                "총 300만원 상당의 개발지원금과, 다양한 게임개발 관련 이벤트 경품(기계식 키보드 등)이 제공됩니다.\n" +
                "\n" +
                "게임잼 신청자에겐 무료로 \n" +
                "11/26(토) \"개발자를 위한 도트 디자인 입문 - 도트 클리커 게임 만들기\" http://onoffmix.com/event/84246\n" +
                "12/03(토) \"슈팅 게임 제작을 통한 Unity3d의 기본기능 익히기\" http://onoffmix.com/event/84245\n" +
                "과정에 참여할 수 있는 기회를 제공해 드립니다.\n");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_POST_NUMBER, 3);
        mDB.insert(Post_Contract.FeedEntry.TABLE_NAME, null, v);
        */

        // 위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                Search_ListViewItem item = (Search_ListViewItem) parent.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;
                String descStr = item.getDesc() ;
                Drawable iconDrawable = item.getIcon() ;

                String toast = Integer.toString(position) + "번 Item";

                if(position == 3) {
                    Intent intent = new Intent(SearchResult.this, Details.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(SearchResult.this, toast, Toast.LENGTH_SHORT).show();
                }
            }
        }) ;
    }

    public void searchPost(View view) {

        EditText et;

        et = (EditText) findViewById(R.id.SearchText);
        String searchString = et.getText().toString();

        dbSearch(searchString);
    }
    public void dbSearch(String s) {
        if (s.length() > 0) {
            String[] mProjection = {
                    Post_Contract.FeedEntry._ID,
                    Post_Contract.FeedEntry.COLUMN_NAME_POST_NAME,
                    Post_Contract.FeedEntry.COLUMN_NAME_DESCRIPTION
            };
            String mSelectionClauses = Post_Contract.FeedEntry.COLUMN_NAME_POST_NAME + " like '%" + s + "%'";
            Cursor c = cr.query(Content_URI, mProjection , mSelectionClauses, null, null);
            Toast.makeText(this, "Search Keyword " + s , Toast.LENGTH_LONG).show();

            while (c.moveToNext()) {
                String pName = c.getString(c.getColumnIndex(Post_Contract.FeedEntry.COLUMN_NAME_POST_NAME));
                String pDesc = c.getString(c.getColumnIndex(Post_Contract.FeedEntry.COLUMN_NAME_DESCRIPTION));
                if(pDesc.length() > 33) {
                    pDesc = pDesc.substring(0, 30);
                    pDesc += "...";
                }
                adapter.addItem(pName, pDesc);
            }
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "Put keyword on EditText", Toast.LENGTH_LONG).show();
        }
    }
}
