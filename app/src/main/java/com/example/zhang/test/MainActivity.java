package com.example.zhang.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.zhang.test.R;

public class MainActivity extends AppCompatActivity {
    int count;
    Button add, sub;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        count = 0;
        text = (TextView)findViewById(R.id.textValue);
        add = (Button)findViewById(R.id.bAdd);
        sub = (Button)findViewById(R.id.bSub);

        add.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                count++;
                text.setText("Total is " + count);
            }
        });
        sub.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                count--;
                text.setText("Total is " + count);
            }
        });
    }
}
