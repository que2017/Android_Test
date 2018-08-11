package com.example.zhang.test;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.example.zhang.test.R;

public class GFXSurface extends Activity implements View.OnTouchListener{
    Things01Surface mySurfaceView;
    float x, y, sX, sY, fX, fY, dX, dY, aniX, aniY, scaledX, scaledY;
    Bitmap test, flower;

    @Override
    protected void onResume() {
        super.onResume();
        mySurfaceView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mySurfaceView.pause();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mySurfaceView = new Things01Surface(this);
        mySurfaceView.setOnTouchListener(this);
        setContentView(mySurfaceView);

        x = 0;
        y = 0;
        sX = sY = 0;
        fX = fY = 0;
        dX = dY = aniX = aniY = scaledX = scaledY = 0;
        test = BitmapFactory.decodeResource(getResources(), R.drawable.greenball);
        flower = BitmapFactory.decodeResource(getResources(), R.drawable.icon3);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        x = event.getX();
        y = event.getY();
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                dX = dY = aniX = aniY = scaledX = scaledY = 0;
                fX = fY = 0;
                sX = event.getX();
                sY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                fX = event.getX();
                fY = event.getY();
                dX = fX - sX;
                dY = fY - sY;
                scaledX = dX/30;
                scaledY = dY/30;
                x = y = 0;
                break;
        }
        return true;
    }
    class Things01Surface extends SurfaceView implements Runnable{
        SurfaceHolder myHolder;
        Thread myThread = null;
        boolean isRunning = false;

        public Things01Surface(Context context) {
            super(context);
            myHolder = getHolder();
        }

        @Override
        public void run() {
            while(isRunning) {
                if(!myHolder.getSurface().isValid()) {
                    continue;
                }
                Canvas canvas = myHolder.lockCanvas();
                canvas.drawRGB(2, 2, 150);

                if(x!=0 && y!=0) {
                    canvas.drawBitmap(test, x - test.getWidth()/2, y - test.getHeight()/2, null);
                }
                if(sX!=0 && sY!=0){
                    canvas.drawBitmap(flower, sX-flower.getWidth()/2, sY-flower.getHeight()/2, null);
                }
                if(fX!=0 && fY!=0){
                    canvas.drawBitmap(flower, fX-flower.getWidth()/2, fY-flower.getHeight()/2, null);
                    canvas.drawBitmap(test, fX - test.getWidth()/2 - aniX, fY - test.getHeight()/2 - aniY, null);
                    try {
                        myThread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                aniX += scaledX;
                aniY += scaledY;
                myHolder.unlockCanvasAndPost(canvas);
            }
        }

        public void pause(){
            isRunning = false;
            while(true){
                try {
                    myThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            myThread = null;
        }

        public void resume(){
            isRunning = true;
            myThread = new Thread(this);
            myThread.start();
        }
    }
}
