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
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_LIMIT, 30);
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_CURRENT, 28);
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_DESCRIPTION, "A-CUBE GAME JAM은 기획자, 프로그래머, 아티스트로 나누어져 각 직군들이 즉석해서 하나의 팀을 만들고 정해진 시간동안 게임을 개발해 보는 기술 시연의 장으로 색다른 아이디어를 현실화 시켜보고 싶었으나 시간과 장소에 대한 제약때문에 현실화 하지 못한 게임 개발자들이 참가하여 주어진 주제에 맞추어 게임개발을 하는 게임개발자들의 축제입니다.");
        v.put(Post_Contract.FeedEntry.COLUMN_NAME_POST_NUMBER, 1);
        cr.insert(Content_URI, v);

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
