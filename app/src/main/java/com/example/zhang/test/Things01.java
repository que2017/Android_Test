package com.example.zhang.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

import com.example.zhang.test.R;

public class Things01 extends View {
    Bitmap gBall;
    float y;
    Typeface font;
    public Things01(Context context) {
        super(context);
        gBall = BitmapFactory.decodeResource(getResources(), R.drawable.greenball);
        y = 0;
        font = Typeface.createFromAsset(context.getAssets(), "G-Unit.TTF");
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        Paint textPaint = new Paint();
        textPaint.setARGB(50, 255,10,50);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(50);
        textPaint.setTypeface(font);
        canvas.drawText("game", canvas.getWidth()/2, 100, textPaint);

        canvas.drawBitmap(gBall, canvas.getWidth()/2, y, null);
        if(y > canvas.getHeight()){
            y = 0;
        }
        y += 10;

        Rect middleRect = new Rect();
        middleRect.set(0, 300, canvas.getWidth(), 400);
        Paint myBlue = new Paint();
        myBlue.setColor(Color.BLUE);
        canvas.drawRect(middleRect, myBlue);
        invalidate();
    }
}
