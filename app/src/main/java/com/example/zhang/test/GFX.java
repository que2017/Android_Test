package com.example.zhang.test;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;

public class GFX extends Activity {
    Things01 myView;
    PowerManager pm;
    PowerManager.WakeLock wl;

    @Override
    protected void onPause() {
        super.onPause();
        wl.release();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // wake-lock
        pm = (PowerManager)getSystemService(Context.POWER_SERVICE);
        wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "whatever");
        super.onCreate(savedInstanceState);
        wl.acquire();
        myView = new Things01(this);
        setContentView(myView);
    }
}
