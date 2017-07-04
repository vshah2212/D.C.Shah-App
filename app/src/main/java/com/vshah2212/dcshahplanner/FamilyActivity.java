package com.vshah2212.dcshahplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FamilyActivity extends AppCompatActivity {
RelativeLayout RDShah,KDShah,CDShah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);

        RDShah = (RelativeLayout) findViewById(R.id.RDLay);
        KDShah = (RelativeLayout) findViewById(R.id.KDLay);
        CDShah = (RelativeLayout) findViewById(R.id.CDLay);

        RDShah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FamilyActivity.this,RDShahActivity.class);
                startActivity(i);
            }
        });

        KDShah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FamilyActivity.this,KDShahActivity.class);
                startActivity(i);
            }
        });

        CDShah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FamilyActivity.this,CDShahActivity.class);
                startActivity(i);
            }
        });


    }
}
