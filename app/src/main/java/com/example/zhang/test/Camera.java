package com.example.zhang.test;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
//import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

import static com.example.zhang.test.R.drawable.ic_launcher_background;

public class Camera extends Activity implements View.OnClickListener{
    ImageButton ib;
    Button b;
    ImageView iv;
    Intent i;
    final static int cameraData = 0;
    Bitmap bmp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);
        initialize();

        @SuppressLint("ResourceType") InputStream is = getResources().openRawResource(ic_launcher_background);
        bmp = BitmapFactory.decodeStream(is);
    }

    private void initialize() {
        iv = (ImageView)findViewById(R.id.ivPhoto);
        ib = (ImageButton)findViewById(R.id.ibTakePic);
        b = (Button)findViewById(R.id.bSetWall);

        ib.setOnClickListener(this);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.ibTakePic:
                i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, cameraData);
                break;
            case R.id.bSetWall:
                try {
                    getApplicationContext().setWallpaper(bmp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            bmp = (Bitmap)extras.get("data");
            iv.setImageBitmap(bmp);
        }
    }
}
