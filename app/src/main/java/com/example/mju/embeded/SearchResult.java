//Created at 17 Oct 16 by MH K

package com.example.mju.embeded;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class SearchResult extends AppCompatActivity {
    ListView listview ;
    Search_ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        // Adapter 생성
        adapter = new Search_ListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);

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

//    public void searchPost() {
//        Post_DbHelper pDBHelper;
//        SQLiteDatabase pDB;
//        EditText et;
//
//        et = (EditText) findViewById(R.id.SearchText);
//        String searchString = et.getText().toString();
//
//        if(searchString.length() > 1) {
//            pDBHelper = new Post_DbHelper(getApplicationContext());
//            pDB = pDBHelper.getReadableDatabase();
//
//
//            Cursor c = pDB.rawQuery("Select * from " + searchString, null);
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
