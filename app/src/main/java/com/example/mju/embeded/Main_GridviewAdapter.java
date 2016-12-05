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
 * Created by Bini2 on 2016-11-25.
 */

public class Main_GridviewAdapter extends BaseAdapter{
    private List<Item> items = new ArrayList<Item>();
    ArrayList<HashMap<String,String>> mList = new ArrayList<HashMap<String, String>>();
    private LayoutInflater inflater;
    private SQLiteDatabase mDB;
    Cursor mCursor;
    private Context mContext;

    public Main_GridviewAdapter(Context context){
        inflater = LayoutInflater.from(context);
        mContext = context;
        // DbHelper 등록 및 DB연동
        Post_DbHelper mDbHelper = new Post_DbHelper(context);
        mDB = mDbHelper.getWritableDatabase();

        // 원하는 Db값 리스트에 저장
        mCursor = mDB.query("post_table", new String[]{"_id","post_name","img_path"}, null,null,null,null,"_id","5");
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
        items.add(new Item(""+mList.get(0).get("post_name").toString(), ""+mList.get(0).get("img_path").toString()+"0"));

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
        ImageView image = (ImageView)view.getTag(R.id.main_imageView);
        image.setImageResource(mContext.getResources().getIdentifier(item.img_path,"drawable",mContext.getPackageName()));

        // Title 설정
        TextView title = (TextView)view.getTag(R.id.main_textView);
        title.setText(item.title);

        return view;
    }

    private class Item
    {
        String title;
        String img_path;

        Item(String title, String image)
        {
            this.title = title;
            this.img_path = image;
        }
    }
}
