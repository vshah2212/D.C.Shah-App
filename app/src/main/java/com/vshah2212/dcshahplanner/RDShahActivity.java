package com.vshah2212.dcshahplanner;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RDShahActivity extends AppCompatActivity {
    TextView RDSudhir,RDKishor,RDKetan,RDJayesh,RDNaina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rdshah);

        RDSudhir = (TextView) findViewById(R.id.RDSudhir);
        RDSudhir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RDShahActivity.this,SudhirActivity.class);
                startActivity(i);
            }
        });
        RDKishor = (TextView) findViewById(R.id.RDKishor);
        RDKishor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RDShahActivity.this,KishorActivity.class);
                startActivity(i);
            }
        });
        RDKetan = (TextView) findViewById(R.id.RDKetan);
        RDKetan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RDShahActivity.this,KetanActivity.class);
                startActivity(i);
            }
        });
        RDJayesh = (TextView) findViewById(R.id.RDJayesh);
        RDJayesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RDShahActivity.this,JayeshActivity.class);
                startActivity(i);
            }
        });
        RDNaina = (TextView) findViewById(R.id.RDNaina);
        RDNaina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RDShahActivity.this,NainaActivity.class);
                startActivity(i);
            }
        });

    }
}
