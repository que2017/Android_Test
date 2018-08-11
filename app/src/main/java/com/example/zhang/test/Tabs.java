package com.example.zhang.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.zhang.test.R;

public class Tabs extends Activity implements View.OnClickListener {
    TabHost th;
    long start, stop;
    TextView showResults;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);

        th = (TabHost)findViewById(R.id.tabhost);
        th.setup();
        // 加载第1个选项卡
        TabHost.TabSpec specs = th.newTabSpec("tag1");
        specs.setContent(R.id.tab1);
        specs.setIndicator("StopWatch");
        th.addTab(specs);
        // 加载第2个选项卡
        specs = th.newTabSpec("tag1");
        specs.setContent(R.id.tab2);
        specs.setIndicator("Tab 2");
        th.addTab(specs);
        // 加载第3个选项卡
        specs = th.newTabSpec("tag1");
        specs.setContent(R.id.tab3);
        specs.setIndicator("Add a tab");
        th.addTab(specs);

        Button newTab = (Button)findViewById(R.id.bAddTab);
        Button bStart = (Button)findViewById(R.id.bStartWatch);
        Button bStop = (Button)findViewById(R.id.bStopWatch);
        showResults = (TextView)findViewById(R.id.tvShowResults);

        newTab.setOnClickListener(this);
        bStart.setOnClickListener(this);
        bStop.setOnClickListener(this);

        start = 0;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bAddTab:
                TabHost.TabSpec mySpec = th.newTabSpec("tag1");
                mySpec.setContent(new TabHost.TabContentFactory() {
                    @Override
                    public View createTabContent(String tag) {
                        TextView text = new TextView(Tabs.this);
                        text.setText("New Tab Created");
                        return text;
                    }
                });
                mySpec.setIndicator("NewT");
                th.addTab(mySpec);
                break;
            case R.id.bStartWatch:
                start = System.currentTimeMillis();
                break;
            case R.id.bStopWatch:
                stop = System.currentTimeMillis();
                if(start!=0){
                    long result = stop - start;
                    int millis, second, min, hour;
                    millis = (int) (result%1000);
                    second = (int) (result/1000);
                    min = second/60;
                    hour = min/60;
                    second %= 60;
                    min %= 60;
                    showResults.setText(String.format("%d:%02d:%02d.%d", hour, min, second, millis));
//                    showResults.setText(Long.toString(result));
                }
                break;
        }
    }
}
