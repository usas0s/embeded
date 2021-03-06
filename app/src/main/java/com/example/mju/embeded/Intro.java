package com.example.mju.embeded;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*
        ~ Copyright (C) 컴퓨터공학과 60132291 오지훈
*/

// 앱 시작 시 호출되는 인트로
public class Intro extends AppCompatActivity {
    private double rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Intent intent = new Intent(this, Service_SoundEffect.class);
        intent.putExtra("sound", R.raw.cast01);
        startService(intent);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(Intro.this,
                        MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        }, 500);
    }
}
