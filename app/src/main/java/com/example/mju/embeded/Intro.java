package com.example.mju.embeded;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*
        ~ Copyright (C) 컴퓨터공학과 60132291 오지훈
*/

public class Intro extends AppCompatActivity {
    private double rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        SoundPool sound_pool;
        sound_pool = new SoundPool( 10, AudioManager.STREAM_MUSIC, 0 );
        int SoundID = sound_pool.load( this, R.raw.cast01, 1 ); // R.raw.sound에서 "sound"는 사운드 파일명
        sound_pool.play( SoundID, 1f, 1f, 0, 0, 1f );



        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(Intro.this,
                        MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        }, 2000);
    }
}
