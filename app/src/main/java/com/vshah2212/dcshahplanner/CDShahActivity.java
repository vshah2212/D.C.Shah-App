package com.vshah2212.dcshahplanner;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CDShahActivity extends AppCompatActivity {
    Button call,call2;
    TextView CDAmit,CDDeepak,CDDipti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cdshah);

        call = (Button) findViewById(R.id.CDCall);
        final String phone_no="919821004819";

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

        call2 = (Button) findViewById(R.id.CDCall2);
        final String phone_no2="02223690067";

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

        CDAmit = (TextView) findViewById(R.id.CDAmit);
        CDAmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CDShahActivity.this,AmitActivity.class);
                startActivity(i);
            }
        });
        CDDeepak = (TextView) findViewById(R.id.CDDeepak);
        CDDeepak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CDShahActivity.this,DeepakActivity.class);
                startActivity(i);
            }
        });
        CDDipti = (TextView) findViewById(R.id.CDDipti);
        CDDipti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CDShahActivity.this,DiptiActivity.class);
                startActivity(i);
            }
        });




        
    }
}
