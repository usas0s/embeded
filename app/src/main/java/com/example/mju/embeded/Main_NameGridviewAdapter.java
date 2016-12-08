package com.example.mju.embeded;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Copyright (C) 컴퓨터공학과 60112320 김동빈
 */

public class Main_NameGridviewAdapter extends BaseAdapter{
    private List<Main_GridviewItem> items = new ArrayList<Main_GridviewItem>();
    ArrayList<HashMap<String,String>> mList = new ArrayList<HashMap<String, String>>();
    private LayoutInflater inflater;
    private SQLiteDatabase mDB;
    Cursor mCursor;
    private Context mContext;
    ImageView image;

    public Main_NameGridviewAdapter(Context context){
        inflater = LayoutInflater.from(context);
        mContext = context;

        // DbHelper 등록 및 DB연동
        myDBHelper mDbHelper = new myDBHelper(context);
        mDB = mDbHelper.getWritableDatabase();

        // 원하는 Db값 리스트에 저장
        mCursor = mDB.query("post_table", new String[]{"post_number","post_name","img_path","limitation","present"}, null,null,null,null,"post_name asc");
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
        for(int i=0;i<mList.size();i++){
            items.add(new Main_GridviewItem(
                    Integer.parseInt(mList.get(i).get("post_number")),
                    ""+mList.get(i).get("post_name").toString(),
                    ""+mList.get(i).get("img_path").toString()+"0",
                    Integer.parseInt(mList.get(i).get("limitation")),
                    Integer.parseInt(mList.get(i).get("present"))));
        }
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null){
            view = inflater.inflate(R.layout.main_grid_item, parent, false);
            view.setTag(R.id.main_imageView, view.findViewById(R.id.main_imageView));
            view.setTag(R.id.main_textView, view.findViewById(R.id.main_textView));
        }
        Main_GridviewItem item = (Main_GridviewItem)getItem(position);

        // 대표 Image 설정
        image = (ImageView)view.getTag(R.id.main_imageView);
        image.setImageResource(mContext.getResources().getIdentifier(item.getImgPath(),"drawable",mContext.getPackageName()));

        // Title 설정
        TextView title = (TextView)view.getTag(R.id.main_textView);
        title.setText(item.getPostName());

        return view;
    }
}
