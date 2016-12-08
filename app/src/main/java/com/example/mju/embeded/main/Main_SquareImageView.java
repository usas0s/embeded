package com.example.mju.embeded.main;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Copyright (C) 컴퓨터공학과 60112320 김동빈
 */

public class Main_SquareImageView extends ImageView {
    private View mMainContainer;
    private boolean mIsDirty=false;

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
}
