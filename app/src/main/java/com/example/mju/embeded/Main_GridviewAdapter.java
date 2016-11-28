package com.example.mju.embeded;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bini2 on 2016-11-25.
 */

public class Main_GridviewAdapter extends BaseAdapter{
    private List<Item> items = new ArrayList<Item>();
    private LayoutInflater inflater;

    public Main_GridviewAdapter(Context context){
        inflater = LayoutInflater.from(context);

        items.add(new Item("Image1", Color.GREEN));
        items.add(new Item("Image1", Color.RED));
        items.add(new Item("Image1", Color.BLUE));
        items.add(new Item("Image1", Color.GRAY));
        items.add(new Item("Image1", Color.YELLOW));

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
