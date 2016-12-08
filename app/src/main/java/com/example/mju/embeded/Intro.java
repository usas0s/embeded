package com.example.mju.embeded;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mju.embeded.main.MainActivity;

/*
        ~ Copyright (C) 컴퓨터공학과 60132291 오지훈
*/

public class Intro extends AppCompatActivity {
    private double rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        new SoundEffects(this, R.raw.cast01);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(Intro.this,
                        MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        }, 10); //TODO 개발완료후 10 -> 1500으로 복구
    }
}
