package com.example.mju.embeded;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.IBinder;//

/*
         ~ Copyright (C) 컴퓨터공학과 60132291 오지훈
*/

public class Service_SoundEffect extends IntentService {
    private int resid;

    @Override
    protected void onHandleIntent(Intent intent) {
        resid = intent.getIntExtra("sound", 1);
        SoundPool sound_pool = new SoundPool( 10, AudioManager.STREAM_MUSIC, 0 );
        sound_pool.load( this, resid, 1 );

        sound_pool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener(){
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId,int status) {
                soundPool.play( sampleId, 1f, 1f, 1, 0, 1f );
            }
        });

    }

    public Service_SoundEffect() {
        super("Service_SoundEffect");
    }
}
