package com.example.zhang.test;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

public class SoundStuff extends Activity implements View.OnClickListener, View.OnLongClickListener{
    SoundPool sp;
    int explosion = 0;

    MediaPlayer mp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View v = new View(this);
        setContentView(v);
        v.setOnClickListener(this);
        v.setOnLongClickListener(this);

        sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        explosion = sp.load(this, R.raw.explosion, 1);

        mp = MediaPlayer.create(this, R.raw.m2m);
    }

    @Override
    public void onClick(View v) {
        if(explosion!=0) {
            sp.play(explosion, 1, 1, 0, 0, 1);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        mp.start();
        return false;
    }
}
