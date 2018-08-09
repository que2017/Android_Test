package com.example.zhang.test;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SQLiteExample extends Activity implements View.OnClickListener {
    Button sqlUpdate, sqlView, sqlModify, sqlGetInfo, sqlDelete;
    EditText sqlName, sqlHotness, sqlRow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlliteexample);

        sqlUpdate = (Button)findViewById(R.id.bSQLUpdate);
        sqlView = (Button)findViewById(R.id.bSQLOpenView);
        sqlName = (EditText)findViewById(R.id.etSQLName);
        sqlHotness = (EditText)findViewById(R.id.etSQLHotness);

        sqlGetInfo = (Button)findViewById(R.id.bGetInfo);
        sqlModify = (Button)findViewById(R.id.bSQLModify);
        sqlDelete = (Button)findViewById(R.id.bSQLDelete);
        sqlRow = (EditText)findViewById(R.id.etSQLRowInfo);

        sqlUpdate.setOnClickListener(this);
        sqlView.setOnClickListener(this);
        sqlGetInfo.setOnClickListener(this);
        sqlModify.setOnClickListener(this);
        sqlDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bSQLUpdate:
                boolean didItWork = true;
                try {
                    String name = sqlName.getText().toString();
                    String hotness = sqlHotness.getText().toString();

                    HotOrNot entry = new HotOrNot(SQLiteExample.this);
                    entry.open();
                    entry.createEntry(name, hotness);
                    entry.close();
                }catch (Exception e){
                    didItWork = false;
                    String err = e.toString();
                    Dialog d = new Dialog(this);
                    d.setTitle("Oh no!");
                    TextView tv = new TextView(this);
                    tv.setText(err);
                    d.setContentView(tv);
                    d.show();
                }finally {
                    if(didItWork){
                        Dialog d = new Dialog(this);
                        d.setTitle("Yeah!");
                        TextView tv = new TextView(this);
                        tv.setText("Data insert success!");
                        d.setContentView(tv);
                        d.show();
                    }
                }
                break;
            case R.id.bSQLOpenView:
                Intent i = new Intent("com.example.zhang.test.SQLVIEW");
                startActivity(i);
                break;
            case R.id.bGetInfo:
                String s = sqlRow.getText().toString();
                long l = Long.parseLong(s);
                HotOrNot hon = new HotOrNot(this);
                hon.open();
                String retName = hon.getName(l);
                String retHotness = hon.getHotness(l);
                hon.close();

                sqlName.setText(retName);
                sqlHotness.setText(retHotness);
                break;
            case R.id.bSQLModify:
                String str = sqlRow.getText().toString();
                long lRow = Long.parseLong(str);
                String mName = sqlName.getText().toString();
                String mHotness = sqlHotness.getText().toString();
                HotOrNot hotNot = new HotOrNot(this);
                hotNot.open();
                hotNot.updateEntry(lRow, mName, mHotness);
                hotNot.close();
                break;
            case R.id.bSQLDelete:
                break;
        }
    }
}
