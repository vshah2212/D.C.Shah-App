package com.vshah2212.dcshahplanner;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

class UpdateEvent extends AsyncTask<String, Void, String> {

    protected String doInBackground(String... message) {
        HttpClient httpclient;
        HttpGet request;
        HttpResponse response = null;
        String result = "";
        try {
            String send = message[0];
            String[] rec = send.split(",");
            String usn = rec[0];

            String event = rec[1];

            String num = rec[2];

            Log.e("Data sent","Usn="+usn+" event="+event+" num="+num);

            httpclient = new DefaultHttpClient();
            request = new HttpGet("http://dcshahfamily.esy.es/EventUp.php?usn="+usn+"&event="+event+"&num="+num);
            response = httpclient.execute(request);
        } catch (Exception e) {
            result = "error1";
        }

        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));
            String line;
            while ((line = rd.readLine()) != null) {
                result = result + line;
            }
        } catch (Exception e) {
            Log.e("Error2","err "+e);
            result = "error2";
        }
        return result;
    }

    protected void onPostExecute(String result) {

        Log.e("Result", "Result" + result);
        if(result.equalsIgnoreCase("1"))
        {
            Log.e("Updated","Updated");
        }
    }
}
