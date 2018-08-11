package com.example.zhang.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ViewFlipper;

import com.example.zhang.test.R;

public class Flipper extends Activity implements View.OnClickListener {
    ViewFlipper vfp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flipper);

        vfp = (ViewFlipper)findViewById(R.id.vf);
        vfp.setOnClickListener(this);
        vfp.setFlipInterval(1000);
        vfp.startFlipping();
    }

    @Override
    public void onClick(View v) {
        vfp.showNext();
    }
}
