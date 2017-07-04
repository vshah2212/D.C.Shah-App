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

class RegisterUser extends AsyncTask<String, Void, String> {
    Context ctx;
    String usn;

    public RegisterUser(Context context)
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
            String name = rec[0];

            String addr = rec[1];

            String dob = rec[2];

            String phn = rec[3];

            String email = rec[4];

            usn = rec[5];

            String pass = rec[6];

            String bg = rec[7];

            httpclient = new DefaultHttpClient();
            request = new HttpGet("http://dcshahfamily.esy.es/Register.php?name="+name+"&addr="+addr+"&dob="+dob+"&phn="+phn+"&em="+email+"&user="+usn+"&pass="+pass+"&blood="+bg);
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
            Toast.makeText(ctx,"Successfully Registered",Toast.LENGTH_LONG).show();
            Intent i = new Intent(ctx, FirstActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("Register","Yes");
            i.putExtra("Username",usn);
            ctx.startActivity(i);
        }
        else
        {
            Toast.makeText(ctx,"Unable to register please try again",Toast.LENGTH_LONG).show();
        }


    }
}