package com.example.zhang.test;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.zhang.test.R;

public class Menu extends ListActivity {
    String[] classes = {"MainActivity", "TextPlay", "Email",
            "Camera", "Data", "GFX", "GFXSurface", "SoundStuff",
            "Slider", "Tabs", "SimpleBrowser", "Flipper", "SharedPrefs",
            "InternalData", "ExternalData", "SQLiteExample"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 全屏显示
        requestWindowFeature(Window.FEATURE_NO_TITLE); // hide title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); // full screen

        setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, classes));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String cheese = classes[position];
        try {
            Class myClass = Class.forName("com.example.zhang.test." + cheese);
            Intent myIntent = new Intent(Menu.this, myClass);
            startActivity(myIntent);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case R.id.aboutus:
                Intent i = new Intent("com.example.zhang.test.ABOUT");
                startActivity(i);
                break;
            case R.id.preference:
                Intent pref = new Intent("com.example.zhang.test.PREFS");
                startActivity(pref);
                break;
            case R.id.exit:
                finish();
                break;
        }
        return false;
    }

    @Override
        public boolean onCreateOptionsMenu(android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater blowUp = getMenuInflater();
        blowUp.inflate(R.menu.cool_menu, menu);
        return true;
    }
}
