package com.example.zhang.test;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.zhang.test.R;

import java.util.Random;

public class TextPlay extends Activity implements OnClickListener{
    Button chkCmd;
    ToggleButton passTog;
    EditText input;
    TextView display;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);

        chkCmd = (Button)findViewById(R.id.bResults);
        passTog = (ToggleButton)findViewById(R.id.tbPassword);
        input = (EditText)findViewById(R.id.etCommand);
        display = (TextView) findViewById(R.id.tvResults);

        passTog.setOnClickListener(this);

        chkCmd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        System.out.println("aaa\n"+this);
        switch(v.getId()){
            case R.id.bResults:
                String check = input.getText().toString();
                display.setText(check);
                if("left".contentEquals(check)){
                    display.setGravity(Gravity.LEFT);
                }else if("right".contentEquals(check)){
                    display.setGravity(Gravity.RIGHT);
                }else if("center".contentEquals(check)){
                    display.setGravity(Gravity.CENTER);
                }else if("blue".contentEquals(check)){
                    display.setTextColor(Color.BLUE);
                }else if("random".contentEquals(check)){
                    Random crazy = new Random();
                    display.setText("Random!!");
                    display.setTextSize(crazy.nextInt(100));
                    display.setTextColor(Color.rgb(crazy.nextInt(256), crazy.nextInt(256), crazy.nextInt(256)));
                    switch(crazy.nextInt(3)){
                        case 0:
                            display.setGravity(Gravity.LEFT);
                            break;
                        case 1:
                            display.setGravity(Gravity.CENTER);
                            break;
                        case 2:
                            display.setGravity(Gravity.RIGHT);
                            break;
                    }
                }else{
                    display.setText("Not find!!");
                    display.setTextColor(Color.BLACK);
                    display.setTextSize(20);
                }
                break;
            case R.id.tbPassword:
                if(passTog.isChecked()){
                    input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }else{
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                }
                break;
        }
    }
}
