package com.example.mju.embeded;

/**
 * Created by Bini2 on 2016-12-08.
 */

public class Main_GridviewItem {
    private int post_number;
    private String post_name;
    private String img_path;
    private int limitation;
    private int current;

    Main_GridviewItem(int number, String title, String image, int limit, int cur)
    {
        this.post_number = number;
        this.post_name = title;
        this.img_path = image;
        this.limitation = limit;
        this.current = cur;
    }

    public int getPostNumber(){
        return this.post_number;
    }
    public String getPostName(){
        return this.post_name;
    }
    public String getImgPath(){
        return this.img_path;
    }
    public int getLimitation(){
        return this.limitation;
    }
    public int getCurrent(){
        return this.current;
    }
}
