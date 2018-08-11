package com.example.zhang.test;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.example.zhang.test.R;
//import android.support.annotation.Nullable;

public class Splash extends Activity {
    MediaPlayer mySong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        mySong = MediaPlayer.create(Splash.this, R.raw.m2m);
        SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean music = getPrefs.getBoolean("checkbox", true);
        if(music) {
            mySong.start();
        }

        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(10000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent openMainActivity = new Intent("com.example.zhang.test.MENU");
                    startActivity(openMainActivity);
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mySong.release();
        finish();
    }
}
