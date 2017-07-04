package com.vshah2212.dcshahplanner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    Button b1;
    EditText us,pw;
    int noback=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        b1 = (Button) findViewById(R.id.button);
        us = (EditText) findViewById(R.id.editText);
        pw = (EditText) findViewById(R.id.editText2);
        String usn;

        Intent i = getIntent();
        if(i.hasExtra("Register")) {
            usn = i.getStringExtra("Username");
         noback = 1;
            us.setText(usn);
        }



        SharedPreferences prefs = getSharedPreferences("LoginPref", MODE_PRIVATE);
        String restoredText = prefs.getString("text", null);
        if (restoredText != null) {
            String ID = prefs.getString("Username", "No name defined");//"No name defined" is the default value.
            String PASS = prefs.getString("Password","0000"); //0 is the default value.
            String msg = ID+","+PASS;
            new CheckLogin(getApplicationContext()).execute(msg);
        }
        else {
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String user = us.getText().toString();
                    String psw = pw.getText().toString();

                    String message = user + "," + psw;
                    Log.e("check", "message=" + message);

                    new CheckLogin(getApplicationContext()).execute(message);


                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        if(noback==1)
        {
        }
        else {
            super.onBackPressed();
        }
    }
}
