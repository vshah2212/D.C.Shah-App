package com.vshah2212.dcshahplanner;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

import static android.content.Context.MODE_PRIVATE;

class PutChat extends AsyncTask<String, Void, String> {
    Context ctx;
    String name,msg;

    public PutChat(Context context)
    {
        ctx = context;
    }


    protected String doInBackground(String... message) {
        HttpClient httpclient;
        HttpGet request;
        HttpResponse response = null;
        String result = "";
        try {
            String send = message[0];
            String[] rec = send.split(",");
            name = rec[0];

            msg = rec[1];

            Log.e("Check", "name=" + name + " msg=" + msg);

            httpclient = new DefaultHttpClient();
            request = new HttpGet("http://dcshahfamily.esy.es/putChat.php?name=" + name + "&msg=" + msg);
            response = httpclient.execute(request);
        } catch (Exception e) {
            result = "error1";
        }

        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null) {
                result = result + line;
            }
        } catch (Exception e) {
            result = "error2";
        }
        return result;
    }

    protected void onPostExecute(String result) {

        Log.e("Result", "Result" + result);

        if (result.trim().equalsIgnoreCase("1"))
        {
            Toast.makeText(ctx,"Message sent!",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(ctx,"Invalid ID or PASSWORD",Toast.LENGTH_LONG).show();
        }

    }
}