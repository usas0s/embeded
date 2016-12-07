package com.example.mju.embeded;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.LinearGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
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

public class Main_LimitGridviewAdapter extends BaseAdapter{
    private List<Item> items = new ArrayList<Item>();
    ArrayList<HashMap<String,String>> mList = new ArrayList<HashMap<String, String>>();
    private LayoutInflater inflater;
    private SQLiteDatabase mDB;
    Cursor mCursor;
    private Context mContext;
    ImageView image;

    public Main_LimitGridviewAdapter(Context context){
        int limitation, present;
        int gap[] ={};
        inflater = LayoutInflater.from(context);
        mContext = context;
        // DbHelper 등록 및 DB연동
        myDBHelper mDbHelper = new myDBHelper(context);
        mDB = mDbHelper.getWritableDatabase();

        // 원하는 Db값 리스트에 저장
        mCursor = mDB.query("post_table", new String[]{"limitation","present","post_name","img_path"}, null,null,null,null,"_id","5");
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
            limitation = Integer.parseInt(mList.get(i).get("limitation").toString());
            present = Integer.parseInt(mList.get(i).get("present").toString());
            gap[i] = limitation - present;
        }
        for(int i=0;i<mList.size();i++){
            items.add(new Item(""+mList.get(i).get("post_name").toString(), ""+mList.get(i).get("img_path").toString()+"0"));
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
        Item item = (Item)getItem(position);

        // 대표 Image 설정
        image = (ImageView)view.getTag(R.id.main_imageView);
        image.setImageResource(mContext.getResources().getIdentifier(item.img_path,"drawable",mContext.getPackageName()));


        // Title 설정
        TextView title = (TextView)view.getTag(R.id.main_textView);
        title.setText(item.title);

        return view;
    }

    private void roundRectShape() {
        float[] outerR = new float[] { 12, 12, 12, 12, 0, 0, 0, 0 };
        RectF inset = new RectF(6, 6, 6, 6);
        float[] innerR = new float[] { 12, 12, 0, 0, 12, 12, 0, 0 };
        ShapeDrawable drawable = new ShapeDrawable(new RoundRectShape(outerR,inset, innerR));
        drawable.setIntrinsicWidth(200);
        drawable.setIntrinsicHeight(100);
        drawable.getPaint().setShader(new LinearGradient(0, 0, 50, 50, new int[] {0xFFFF0000, 0XFF00FF00, 0XFF0000FF }, null, Shader.TileMode.REPEAT));
        image.setImageDrawable(drawable);
    }

    private class Item {
        String title;
        String img_path;

        Item(String title, String image)
        {
            this.title = title;
            this.img_path = image;
        }
    }
}
