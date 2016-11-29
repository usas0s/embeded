//Created at 17 Oct 16 by MH K

package com.example.mju.embeded;

import android.content.ContentResolver;
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
    private static final String URI = "content://com.example.mju.embeded/Post_DB";
    Post_DbHelper pDBHelper;
    SQLiteDatabase pDB;
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

//        // 첫 번째 아이템 추가.
//        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.listtest_1),
//                "Box", "Account Box Black 36dp") ;
//        // 두 번째 아이템 추가.
//        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.listtest_2),
//                "Circle", "Account Circle Black 36dp") ;
//        // 세 번째 아이템 추가.
//        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.listtest_3),
//                "Ind", "Assignment Ind Black 36dp") ;
//        // 테스트 케이스
//        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.listtest_3),
//                "TEST", "to Detail") ;
//        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.listtest_3),
//                "Ind", "Assignment Ind Black 36dp") ;
//        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.listtest_3),
//                "Ind", "Assignment Ind Black 36dp") ;
//        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.listtest_3),
//                "Ind", "Assignment Ind Black 36dp") ;
//        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.listtest_3),
//                "Ind", "Assignment Ind Black 36dp") ;
//        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.listtest_3),
//                "Ind", "Assignment Ind Black 36dp") ;
//        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.listtest_3),
//                "Ind", "Assignment Ind Black 36dp") ;
//        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.listtest_3),
//                "Ind", "Assignment Ind Black 36dp") ;

        Cursor c = cr.query(Uri.parse(URI), null, null, null, null);

        int n = 0;
        while(c.moveToNext()) {
//            Drawable path = null;
            String p_name = c.getString(c.getColumnIndex(Post_Contract.FeedEntry.COLUMN_NAME_POST_NAME));
//            String p_desc = c.getString(c.getColumnIndex(Post_Contract.FeedEntry.COLUMN_NAME_DESCRIPTION));
//            adapter.addItem(p_name, p_desc);
            n++;
            adapter.addItem("title " + n, "desc");
        }

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

    public void searchPost() {

        EditText et;

        et = (EditText) findViewById(R.id.SearchText);
        String searchString = et.getText().toString();

//        dbSearch(searchString);
    }
//    public void dbSearch(String s) {
//        if(s.length() > 1) {
//
//            while (c.moveToNext()) {
//                String pName = c.getString(c.getColumnIndex(Post_Contract.FeedEntry.COLUMN_NAME_POST_NAME));
//                String pDesc = c.getString(c.getColumnIndex(Post_Contract.FeedEntry.COLUMN_NAME_DESCRIPTION));
//                adapter.addItem(null, pName, pDesc);
//            }
//            adapter.notifyDataSetChanged();
//        } else {
//            Toast.makeText(this, "Put keyword on EditText", Toast.LENGTH_LONG).show();
//        }
//    }
}
