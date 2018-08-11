package com.example.zhang.test;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhang.test.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ExternalData extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private TextView canWrite, canRead;
    private String state;

    Boolean canW, canR;
    String[] paths = {"Music", "Picture", "Download"};
    Spinner spinner;

    File path = null;
    File file = null;

    EditText saveFile;
    Button confirm, save;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.externaldata);

        canWrite = (TextView)findViewById(R.id.tvCanWrite);
        canRead = (TextView)findViewById(R.id.tvCanRead);

        saveFile  = (EditText)findViewById(R.id.etSaveAs);
        confirm = (Button)findViewById(R.id.bConfirmSaveAs);
        save = (Button)findViewById(R.id.bSaveAs);
        confirm.setOnClickListener(this);
        save.setOnClickListener(this);

        checkState();

        spinner = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ExternalData.this,
                android.R.layout.simple_spinner_item, paths);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    private void checkState() {
        state = Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED)){
            canWrite.setText("true");
            canRead.setText("true");
            canW = canR = true;
        }else if(state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
            canWrite.setText("false");
            canRead.setText("true");
            canW = false;
            canR = true;
        } else {
            canW = canR = false;
        }
    }

    //    @SuppressLint("NewApi")
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int pos = spinner.getSelectedItemPosition();
        switch(pos){
            case 0:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
                break;
            case 1:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                break;
            case 2:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bConfirmSaveAs:
                save.setVisibility(View.VISIBLE);
                break;
            case R.id.bSaveAs:
                String f = saveFile.getText().toString();
                file = new File(path, f);
                checkState();
                if(canW == canR == true){
                    path.mkdirs();
                    try {
                        InputStream is = getResources().openRawResource(R.drawable.greenball);
                        OutputStream os = new FileOutputStream(file);
                        byte[] data  = new byte[is.available()];
                        is.read(data);
                        os.write(data);
                        is.close();
                        os.close();
                        Toast t = Toast.makeText(ExternalData.this, "File save success", Toast.LENGTH_LONG);
                        t.show();

                        MediaScannerConnection.scanFile(ExternalData.this, new String[]{file.toString()},
                                null, new MediaScannerConnection.OnScanCompletedListener() {
                                    @Override
                                    public void onScanCompleted(String path, Uri uri) {
                                        Toast.makeText(ExternalData.this, "Scan finish", Toast.LENGTH_LONG).show();
                                    }
                                });
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}
