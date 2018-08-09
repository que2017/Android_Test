package com.example.zhang.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

public class SQLView extends Activity {
    TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlview);

        tv = (TextView)findViewById(R.id.tvSQLInfo);

        HotOrNot info = new HotOrNot(this);
        info.open();
        // drag data from the SQLite
        String data = info.getData();
        info.close();
        tv.setText(data);
    }
}
