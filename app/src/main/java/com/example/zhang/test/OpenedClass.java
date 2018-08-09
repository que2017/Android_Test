package com.example.zhang.test;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class OpenedClass extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    TextView question, test;
    Button returnData;
    RadioGroup selectList;
    String gotBread, setData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send);
        initialize();

        returnData.setOnClickListener(this);
        selectList.setOnCheckedChangeListener(this);

        SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String et = getData.getString("name", "ustc");
        String values = getData.getString("list", "4");
        if("1".contentEquals(values)){
            question.setText(et);
        }

//        Bundle gotBasket = getIntent().getExtras();
//        gotBread = gotBasket.getString("key");
//        question.setText(gotBread);
    }

    private void initialize() {
        question = (TextView)findViewById(R.id.tvQuestion);
        test = (TextView) findViewById(R.id.tvText);
        returnData = (Button)findViewById(R.id.bReturn);
        selectList = (RadioGroup)findViewById(R.id.rgAnswer);
    }

    @Override
    public void onClick(View v) {
        Intent person = new Intent();
        Bundle backPack = new Bundle();
        backPack.putString("answer", setData);
        person.putExtras(backPack);
        setResult(RESULT_OK, person);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch(checkedId){
            case R.id.rCrazy:
                setData = "Probably right!";
                break;
            case R.id.rKind:
                setData = "Definitely right!";
                break;
            case R.id.rBoth:
                setData = "Right!";
                break;
        }
        test.setText(setData);
    }
}
