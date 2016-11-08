package com.example.mju.embeded;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "참가 신청하기", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//        ImageView img = (ImageView)findViewById(R.id.imageView1);
//        Resources res = getResources();
//
//        Bitmap bitmap = BitmapFactory.decodeResource(res, android.R.drawable.btn_star_big_on);
//        Bitmap bitmap2 = Bitmap.createScaledBitmap(bitmap, 200, 90, false);
//        img.setImageBitmap(bitmap2);

//        BitmapFactory.Options opts = new BitmapFactory.Options();
//        opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
//        Bitmap bmp = BitmapFactory.decodeResource(getResources(),
//                R.drawable.img1, opts);
//        ImageView view = (ImageView) findViewById(R.id.imageView1);
//        view.setImageBitmap(bmp);

//        <ImageView
//        android:layout_width="match_parent"
//        android:layout_height="match_parent"
//        android:id="@+id/imageView1"/>
    }
}
