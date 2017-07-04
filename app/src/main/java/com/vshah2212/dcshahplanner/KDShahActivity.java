package com.vshah2212.dcshahplanner;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class KDShahActivity extends AppCompatActivity {
    TextView KDManoj,KDKirit,KDJyotsna,KDSonal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kdshah);

        KDManoj = (TextView) findViewById(R.id.KDManoj);
        KDManoj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(KDShahActivity.this,ManojActivity.class);
                startActivity(i);
            }
        });
        KDKirit = (TextView) findViewById(R.id.KDKirit);
        KDKirit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(KDShahActivity.this,KiritActivity.class);
                startActivity(i);
            }
        });
        KDJyotsna = (TextView) findViewById(R.id.KDJyotsna);
        KDJyotsna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(KDShahActivity.this,JyotsnaActivity.class);
                startActivity(i);
            }
        });
        KDSonal = (TextView) findViewById(R.id.KDSonal);
        KDSonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(KDShahActivity.this,SonalActivity.class);
                startActivity(i);
            }
        });

    }
}
