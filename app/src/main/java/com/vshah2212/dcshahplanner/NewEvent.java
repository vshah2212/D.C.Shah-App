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

class NewEvent extends AsyncTask<String, Void, String> {
    Context ctx;
    String usn;

    public NewEvent(Context context)
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
            String evname = rec[0];

            String evdate = rec[1];

            String evloc = rec[2];

            String evgrp = rec[3];

            httpclient = new DefaultHttpClient();
            request = new HttpGet("http://dcshahfamily.esy.es/AddEvent.php?evname="+evname+"&evdate="+evdate+"&evloc="+evloc+"&evgrp="+evgrp);
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
        if (result.trim().equalsIgnoreCase("1"))
        {
            Toast.makeText(ctx,"Successfully Added Event",Toast.LENGTH_LONG).show();
            Intent i = new Intent(ctx, NavActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ctx.startActivity(i);
        }
        else
        {
            Toast.makeText(ctx,"Unable to process request.",Toast.LENGTH_LONG).show();
        }


    }
}
