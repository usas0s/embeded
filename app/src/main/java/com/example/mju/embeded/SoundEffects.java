package com.example.mju.embeded;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * Created by Glisseo on 2016-12-05.
 */

public class SoundEffects {
    private final int SoundID;

    public SoundEffects(Context context, int resid){
        SoundPool sound_pool = new SoundPool( 10, AudioManager.STREAM_MUSIC, 0 );
        SoundID = sound_pool.load( context, resid, 1 ); // R.raw.sound에서 "sound"는 사운드 파일명


        sound_pool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener(){
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId,int status) {
                soundPool.play( SoundID, 1f, 1f, 1, 0, 1f );
            }
        });
    }
}
