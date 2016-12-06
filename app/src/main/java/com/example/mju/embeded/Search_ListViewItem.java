package com.example.mju.embeded;

import android.graphics.drawable.Drawable;

public class Search_ListViewItem {
    private Drawable iconDrawable ;
    private String titleStr ;
    private String descStr ;
    private int postNumber;

    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDesc(String desc) {
        descStr = desc ;
    }
    public void setPostNumber(int pnum) { postNumber = pnum ; }

    public Drawable getIcon() {
        return this.iconDrawable ;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc() {
        return this.descStr ;
    }
    public int getPostNumber() { return this.postNumber ; }
}