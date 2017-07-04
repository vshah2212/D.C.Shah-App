package com.vshah2212.dcshahplanner;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class AvailSend extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avail_send);

        final ProgressDialog loading = ProgressDialog.show(this,"Updating...","Please wait");

        Bundle extras ;
        extras = getIntent().getExtras();
        int size = extras.size();

        String num = getIntent().getStringExtra("Number");
        Log.e("Numin2","Num2:"+num);

        SharedPreferences prefs = getSharedPreferences("LoginPref", MODE_PRIVATE);
        String usn = prefs.getString("Username", "No name defined");
        String msg;

        for (int i = 1; i < size; i++) {

            String txt = extras.getString("Item" + i + "");
            msg = usn+","+txt+","+num;
            Log.e("Data sent","Message="+msg);
            new UpdateEvent().execute(msg);

        }


        Toast.makeText(this,"Successfully Updated",Toast.LENGTH_LONG).show();
        Intent i = new Intent(AvailSend.this,NavActivity.class);
        startActivity(i);
        loading.dismiss();

    }
}
