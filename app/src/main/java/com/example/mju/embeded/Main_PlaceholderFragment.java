package com.example.mju.embeded;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Copyright (C) 컴퓨터공학과 60112320 김동빈
 */

public class Main_PlaceholderFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    ArrayList<HashMap<String,String>> mList = new ArrayList<HashMap<String, String>>();
    private SQLiteDatabase mDB;
    Cursor mCursor;

    public Main_PlaceholderFragment() {
    }

    public static Main_PlaceholderFragment newInstance(int sectionNumber) {
        Main_PlaceholderFragment fragment = new Main_PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);

        final View rootView = inflater.inflate(R.layout.main_fragment, container, false);

        if(sectionNumber == 1){
            GridView gridView = (GridView) rootView.findViewById(R.id.main_gridView);
            gridView.setAdapter(new Main_GeneralGridviewAdapter(getActivity().getApplicationContext()));

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String post_number = dbSearch(position);
                    Intent intent = new Intent(getActivity().getApplicationContext(),Details.class);
                    intent.putExtra("number",Integer.parseInt(post_number));
                    startActivity(intent);
                }
            });
        }else if(sectionNumber == 2){
            GridView gridView = (GridView) rootView.findViewById(R.id.main_gridView);
            gridView.setAdapter(new Main_GeneralGridviewAdapter(getActivity().getApplicationContext()));
        }else{
            GridView gridView = (GridView) rootView.findViewById(R.id.main_gridView);
            gridView.setAdapter(new Main_GeneralGridviewAdapter(getActivity().getApplicationContext()));
        }
        return rootView;
    }

    public String dbSearch(int position){
        String post_number;
        // DbHelper 등록 및 DB연동
        myDBHelper mDbHelper = new myDBHelper(getActivity().getApplicationContext());
        mDB = mDbHelper.getWritableDatabase();

        // 원하는 Db값 리스트에 저장
        mCursor = mDB.query("post_table", new String[]{"post_number"}, null,null,null,null,"_id","5");
        if(mCursor != null){
            if(mCursor.moveToFirst()){
                do{
                    HashMap<String,String> item = new HashMap<String,String>();
                    for(int j=0; j<mCursor.getColumnCount(); j++){
                        item.put(mCursor.getColumnName(j), mCursor.getString(j));
                    }
                    mList.add(item);
                }while(mCursor.moveToNext());
            }
        }
        post_number = mList.get(position).get("post_number").toString();
        return post_number;
    }
}
