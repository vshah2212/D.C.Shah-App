package com.vshah2212.dcshahplanner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import java.io.IOException;

import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (android.os.Build.VERSION.SDK_INT > 9) {
                    StrictMode.ThreadPolicy policy =
                            new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                }


                 String url = "http://dcshahfamily.esy.es/DateUp.php";
                 HttpClient client = new DefaultHttpClient();

                 try {
                    client.execute(new HttpGet(url));
                 } catch(IOException e) {
                     //do something here
                 }

                //subscribeToPushService();

                //Do something after 100ms
                SharedPreferences prefs = getSharedPreferences("LoginPref", MODE_PRIVATE);
                // String restoredText = prefs.getString("text", null);
                //if (restoredText != null) {
                //    String ID = prefs.getString("Username", "No name defined");//"No name defined" is the default value.
                //    String PASS = prefs.getString("Password","0000"); //0 is the default value.
                //    String msg = ID+","+PASS;
                if(prefs.contains("Username")) {
                    Intent i = new Intent(SplashActivity.this,NavActivity.class);
                    startActivity(i);
                }
                // new CheckLogin(getApplicationContext()).execute(msg);
                else {
                    Intent i = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(i);
                }
            }
        }, 1250);



    }
    //private void subscribeToPushService() {
    //    FirebaseMessaging.getInstance().subscribeToTopic("news");
    //
    //    Log.d("AndroidBash", "Subscribed");
    //    Toast.makeText(this, "Subscribed", Toast.LENGTH_SHORT).show();
    //
    //    String token = FirebaseInstanceId.getInstance().getToken();
    //
        // Log and toast
    //    Log.d("AndroidBash", token);
    //    Toast.makeText(this, token, Toast.LENGTH_SHORT).show();
    //}
}
