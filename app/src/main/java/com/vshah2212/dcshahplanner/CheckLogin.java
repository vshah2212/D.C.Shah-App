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

class CheckLogin extends AsyncTask<String, Void, String> {
    Context ctx;
    String us,pw;

    public CheckLogin(Context context)
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
            us = rec[0];

            pw = rec[1];

            Log.e("Check", "us=" + us + " pass=" + pw + "ok");

            httpclient = new DefaultHttpClient();
            request = new HttpGet("http://dcshahfamily.esy.es/Login.php?us=" + us + "&pw=" + pw);
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

        if (result.trim()!="")
        {
            SharedPreferences.Editor editor = ctx.getSharedPreferences("LoginPref", MODE_PRIVATE).edit();
            editor.putString("Username", us);
            editor.putString("Password", pw);
            editor.putString("Name",result);
            editor.putString("text","YES");
            editor.commit();

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

          Intent i = new Intent(ctx,NavActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ctx.startActivity(i);
        }
        else {
            Toast.makeText(ctx,"Invalid ID or PASSWORD",Toast.LENGTH_LONG).show();
            }

        }
    }