package com.example.zhang.test;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import com.example.zhang.test.R;

public class SimpleBrowser extends Activity implements View.OnClickListener {
    EditText url;
    WebView myBrow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simplebrowser);
        myBrow = (WebView)findViewById(R.id.wvBrowser);
        myBrow.setWebViewClient(new myViewClient());
//        myBrow.getSettings().setJavaScriptEnabled(true);
        myBrow.getSettings().setLoadWithOverviewMode(true);
        myBrow.getSettings().setUseWideViewPort(true);
        try {
            myBrow.loadUrl("http://www.baidu.com");
        }catch (Exception e){
            e.printStackTrace();
        }

        Button go = (Button)findViewById(R.id.bGo);
        Button back = (Button)findViewById(R.id.bBack);
        Button refresh = (Button)findViewById(R.id.bRefresh);
        Button forward = (Button)findViewById(R.id.bForward);
        Button clearHistory = (Button)findViewById(R.id.bClear);
        url = (EditText)findViewById(R.id.etURL);

        go.setOnClickListener(this);
        back.setOnClickListener(this);
        refresh.setOnClickListener(this);
        forward.setOnClickListener(this);
        clearHistory.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bGo:
                String theWebsite = url.getText().toString();
                myBrow.loadUrl(theWebsite);
                // 点击Go之后隐藏键盘
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(url.getWindowToken(), 0);
                break;
            case R.id.bBack:
                if(myBrow.canGoBack()) {
                    myBrow.goBack();
                }
                break;
            case R.id.bRefresh:
                myBrow.reload();
                break;
            case R.id.bForward:
                if(myBrow.canGoForward()){
                    myBrow.goForward();
                }
                break;
            case R.id.bClear:
                myBrow.clearHistory();
                break;
        }
    }
}
