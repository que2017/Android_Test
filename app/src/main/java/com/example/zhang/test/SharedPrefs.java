package com.example.zhang.test;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPrefs extends Activity implements View.OnClickListener {
    EditText sharedData;
    TextView dataResults;
    public static String filename = "MySharedString";
    SharedPreferences someData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedpreferences);
        setupVariables();

        someData = getSharedPreferences(filename,0);
    }

    private void setupVariables() {
        Button save = (Button)findViewById(R.id.bSave);
        Button load = (Button)findViewById(R.id.bLoad);
        sharedData = (EditText)findViewById(R.id.etSharedPrefs);
        dataResults = (TextView)findViewById(R.id.tvLoadSharedPrefs);
        save.setOnClickListener(this);
        load.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bSave:
                String strData = sharedData.getText().toString();
                SharedPreferences.Editor editor = someData.edit();
                editor.putString("sharedString", strData);
                editor.commit();
                break;
            case R.id.bLoad:
                someData = getSharedPreferences(filename,0);
                String strReturned = someData.getString("sharedString", "Can't find!");
                dataResults.setText(strReturned);
                break;
        }
    }
}
