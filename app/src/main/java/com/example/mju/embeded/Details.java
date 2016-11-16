package com.example.mju.embeded;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            // floating 버튼 터치 시 참가 신청 페이지 열림.
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "참가 신청하기", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Snackbar snackbar;
                snackbar = Snackbar.make(view, "참가 신청하기", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener()
                        {
                            // 참가 신청 버튼 터치 시 할일
                            @Override
                            public void onClick(View view)
                            {
                                Toast.makeText(getApplicationContext(),"moving to Apply activity",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(),Apply.class);
                                startActivity(intent);
                            }
                        });
                View snackView = snackbar.getView();
                snackView.setBackgroundColor(Color.parseColor("#0000FF"));
                snackbar.show();
            }
        });
    }

    public void showMap(View view)
    {
        Intent intent = new Intent(this,Maps.class);
        startActivity(intent);
    }
}
