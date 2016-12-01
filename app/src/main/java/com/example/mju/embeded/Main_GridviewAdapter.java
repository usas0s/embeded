package com.example.mju.embeded;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
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
    private LayoutInflater inflater;
    private SQLiteDatabase mDB;
    Cursor mCursor;

    public Main_GridviewAdapter(Context context){
        inflater = LayoutInflater.from(context);
        // DB 연동

        Post_DbHelper mDbHelper = new Post_DbHelper(context);
        mDB = mDbHelper.getWritableDatabase();
        mDbHelper.onCreate(mDB);

        mCursor = mDB.query("post_table",
                new String[] {"post_name"}, null, null, null, null, "_id", "5");

        ArrayList<HashMap<String,String>> mList = new ArrayList<HashMap<String,String>>();
        if(mCursor != null) {
            if (mCursor.moveToFirst()) {
                do {
                    HashMap<String,String> item = new HashMap<String,String>();
                    for (int j=0; j<mCursor.getColumnCount(); j++)
                        item.put(mCursor.getColumnName(j), mCursor.getString(j));
                    mList.add(item);
                } while (mCursor.moveToNext());
            }
        }
        //Log.e("test"," "+mList.get(0).get("post_name"));
        //items.add(new Item(""+mList.get(0).get("post_name").toString(), Color.RED));

        items.add(new Item("Image1", Color.GREEN));
        items.add(new Item("Image2", Color.RED));
        items.add(new Item("Image3", Color.BLUE));
        items.add(new Item("Image4", Color.GRAY));
        items.add(new Item("Image5", Color.YELLOW));
        items.add(new Item("Image6", Color.GREEN));
        items.add(new Item("Image7", Color.RED));
        items.add(new Item("Image8", Color.BLUE));
        items.add(new Item("Image9", Color.GRAY));
        items.add(new Item("Image10", Color.YELLOW));

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
    public long getItemId(int i) {
        return items.get(i).colorId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null){
            view = inflater.inflate(R.layout.main_grid_item, parent, false);
            view.setTag(R.id.main_imageView, view.findViewById(R.id.main_imageView));
            view.setTag(R.id.main_textView, view.findViewById(R.id.main_textView));
        }

        ImageView image = (ImageView)view.getTag(R.id.main_imageView);
        TextView title = (TextView)view.getTag(R.id.main_textView);

        Item item = (Item)getItem(position);

        image.setBackgroundColor(item.colorId);
        title.setText(item.name);

        return view;
    }

    private class Item
    {
        final String name;
        final int colorId;

        Item(String name, int drawableId)
        {
            this.name = name;
            this.colorId = drawableId;
        }
    }
}
