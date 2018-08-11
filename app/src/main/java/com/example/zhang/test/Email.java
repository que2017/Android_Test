package com.example.zhang.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.zhang.test.R;

public class Email extends Activity implements OnClickListener{
    EditText personEmail, intro, personName, stupidThings, hatefulAction, outro;
    String emailAdd, beginning, name, stupidAction, hatefulAct, out;
    Button sendEmail;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email);
        initializeVars();
        sendEmail.setOnClickListener(this);
    }

    private void initializeVars(){
        personEmail = (EditText)findViewById(R.id.etEmails);
        intro = (EditText)findViewById(R.id.etIntro);
        personName = (EditText)findViewById(R.id.etName);
        stupidThings = (EditText)findViewById(R.id.etThings);
        hatefulAction = (EditText)findViewById(R.id.etAction);
        outro = (EditText)findViewById(R.id.etOutro);
        sendEmail = (Button) findViewById(R.id.bSendEmail);
    }
    @Override
    public void onClick(View v) {
        convertMethod();
        String[] emailaddress = {emailAdd};
        String message = "Well hello "
                + name
                + " I just want to say "
                + beginning
                + ". Not only that but I hate when you"
                + stupidAction
                + ", that really makes me crazy. I just want to make you"
                + hatefulAct
                + ". Welp, thats all I wanted to chit-chatter about, oh and "
                + out
                + ". Oh also if you get bored you should check out www.baidu.com "
                + '\n' + "PS. I thisk I love you...";
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, emailaddress);
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "I hate you");
        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);

        startActivity(emailIntent);
    }

    private void convertMethod() {
        emailAdd = personEmail.getText().toString();
        beginning = intro.getText().toString();
        name = personName.getText().toString();
        stupidAction = stupidThings.getText().toString();
        hatefulAct = hatefulAction.getText().toString();
        out = outro.getText().toString();
    }
    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }
}
