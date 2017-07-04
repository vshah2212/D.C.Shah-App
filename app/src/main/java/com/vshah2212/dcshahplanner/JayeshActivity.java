package com.vshah2212.dcshahplanner;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class JayeshActivity extends AppCompatActivity {

    Button call,call2,call3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jayesh);

        call = (Button) findViewById(R.id.JayeshCall);
        final String phone_no="919321166747";

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+phone_no));
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                try{
                    startActivity(callIntent); }
                catch (Exception e) {
                }
            }
        });

        call2 = (Button) findViewById(R.id.JayeshCall2);
        final String phone_no2="02228013863";

        call2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+phone_no2));
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                try{
                    startActivity(callIntent); }
                catch (Exception e) {
                }
            }
        });

        call3 = (Button) findViewById(R.id.JagrutiCall);
        final String phone_no3="917738161377";

        call3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+phone_no3));
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                try{
                    startActivity(callIntent); }
                catch (Exception e) {
                }
            }
        });
        
    }
}
