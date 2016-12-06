package com.example.mju.embeded;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Bini2 on 2016-11-25.
 */

public class Main_SquareImageView extends ImageView
{

    public Main_SquareImageView(Context context)
    {
        super(context);
    }

    public Main_SquareImageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public Main_SquareImageView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth()); //Snap to width
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        super.setOnClickListener(l);
    }
}
