package com.vshah2212.dcshahplanner;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;

public class AttendingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attending);

        SharedPreferences prefs = getSharedPreferences("LoginPref", MODE_PRIVATE);
        String username = prefs.getString("Username", "No name defined");

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        String result = null;
        InputStream is = null;

        try{
            HttpClient httpclient = HttpClientBuilder.create().build();
            HttpPost httppost;

            httppost = new HttpPost("http://dcshahfamily.esy.es/getAttending.php?username="+username);

            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();

            //   Log.e("log_tag", "connection success ");
            //   Toast.makeText(getApplicationContext(), "pass", Toast.LENGTH_SHORT).show();
        }
        catch(Exception e)
        {
            //   Log.e("log_tag", "Error in http connection "+e.toString());
            //  Toast.makeText(getApplicationContext(), "Connection fail", Toast.LENGTH_SHORT).show();

        }
        //convert response to string
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            is.close();

            result=sb.toString();
        }
        catch(Exception e)
        {
            //   Log.e("log_tag", "Error converting result "+e.toString());
            //   Toast.makeText(getApplicationContext(), " Input reading fail", Toast.LENGTH_SHORT).show();
        }

        //parse json data
        try
        {

            Log.e("result","Result string in jSON "+result);

            JSONArray jArray = new JSONArray(result);


            // String re=jArray.getString(jArray.length()-1);


            TableLayout tv=(TableLayout)findViewById(R.id.tablex);
            tv.removeAllViewsInLayout();




            int flag=1;

            for(int i=-1;i<jArray.length();i++)

            {




                TableRow tr=new TableRow(this);

                tr.setLayoutParams(new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));




                if(flag==1)
                {

                    TextView b6=new TextView(this);
                    b6.setPadding(0,0,0,0);
                    b6.setText("Event");
                    b6.setTextColor(Color.BLUE);
                    b6.setTextSize(15);
                    tr.addView(b6);


                    TextView b19=new TextView(this);
                    b19.setPadding(40,0, 0, 0);
                    b19.setTextSize(15);
                    b19.setText("Name");
                    b19.setTextColor(Color.BLUE);
                    tr.addView(b19);

                    TextView b20=new TextView(this);
                    b20.setPadding(40,0, 0, 0);
                    b20.setTextSize(15);
                    b20.setText("Number");
                    b20.setTextColor(Color.BLUE);
                    tr.addView(b20);

                    tv.addView(tr);

                    final View vline = new View(this);
                    vline.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 2));
                    vline.setBackgroundColor(Color.BLUE);



                    tv.addView(vline);
                    flag=0;


                }

                else
                {



                    JSONObject json_data = jArray.getJSONObject(i);

                    //Log.i("log_tag","OrderName: "+json_data.getString("Name")+
                    //        ", Status: "+json_data.getString("DOB"));




                    TextView b1=new TextView(this);
                    b1.setPadding(10, 0, 0, 0);
                    b1.setTextSize(15);
                    String stime1=json_data.getString("Event");
                    b1.setText(stime1);
                    b1.setTextColor(Color.RED);
                    tr.addView(b1);

                    TextView b2=new TextView(this);

                    b2.setPadding(40, 0, 0, 0);
                    String stime2=json_data.getString("Name");
                    b2.setText(stime2);
                    b2.setTextColor(Color.RED);
                    b2.setTextSize(15);
                    tr.addView(b2);

                    TextView b3=new TextView(this);
                    b3.setPadding(40, 0, 0, 0);
                    String stime3=json_data.getString("Number");
                    b3.setText(stime2);
                    b3.setTextColor(Color.RED);
                    b3.setTextSize(15);
                    tr.addView(b3);

                    tv.addView(tr);


                    final View vline1 = new View(this);
                    vline1.setLayoutParams(new TableRow.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, 1));
                    vline1.setBackgroundColor(Color.WHITE);
                    tv.addView(vline1);


                }

            }



        }
        catch(JSONException e)
        {
            Log.e("log_tag", "Error parsing data "+e.toString());
            Toast.makeText(this, "JsonArray fail", Toast.LENGTH_SHORT).show();
        }






    }
}
