package com.example.zhang.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Data extends Activity implements View.OnClickListener {
    Button start, startFor;
    EditText sendET;
    TextView gotAnswer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get);
        initialize();
    }

    private void initialize() {
        start = (Button)findViewById(R.id.bSA);
        startFor = (Button)findViewById(R.id.bSAR);
        sendET = (EditText)findViewById(R.id.etSend);
        gotAnswer = (TextView)findViewById(R.id.tvGot);

        start.setOnClickListener(this);
        startFor.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Bundle answer = data.getExtras();
            String s = answer.getString("answer");
            gotAnswer.setText(s);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bSA:
                String bread = sendET.getText().toString();
                // 用Bundle在两个Activity之间传递数据, Bundle是一个map集合
                Bundle basket = new Bundle();
                basket.putString("key", bread);
                Intent a = new Intent(Data.this, OpenedClass.class);
                a.putExtras(basket);
                startActivity(a);
                break;
            case R.id.bSAR:
                Intent i = new Intent(Data.this, OpenedClass.class);
                startActivityForResult(i, 0);
                break;
        }
    }
}
