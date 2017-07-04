package com.vshah2212.dcshahplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    Button b1;
    EditText name,address,dob,phone,email,username,password,blood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        b1 = (Button) findViewById(R.id.register);
        name = (EditText) findViewById(R.id.Name);
        address = (EditText) findViewById(R.id.Address);
        dob = (EditText) findViewById(R.id.DOB);
        phone = (EditText) findViewById(R.id.PhoneNumber);
        email = (EditText) findViewById(R.id.Email);
        username = (EditText) findViewById(R.id.Username);
        password = (EditText) findViewById(R.id.Password);
        blood = (EditText) findViewById(R.id.BloodGroup);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int valid = 1;
                if(name.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(getApplicationContext(), "Enter proper NAME", Toast.LENGTH_LONG).show();
                    valid=0;
                }
                else if(address.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(getApplicationContext(), "Enter proper ADDRESS", Toast.LENGTH_LONG).show();
                    valid=0;
                }
                else if(dob.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(getApplicationContext(), "Enter proper DATE OF BIRTH", Toast.LENGTH_LONG).show();
                    valid=0;
                }
                else if(phone.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(getApplicationContext(), "Enter proper PHONE NUMBER", Toast.LENGTH_LONG).show();
                    valid=0;
                }
                else if(username.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(getApplicationContext(), "USERNAME cannot be empty", Toast.LENGTH_LONG).show();
                    valid=0;
                }
                else if(password.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(getApplicationContext(), "PASSWORD cannot be blank", Toast.LENGTH_LONG).show();
                    valid=0;
                }

                String newnm = name.getText().toString().trim();
                int ind=newnm.indexOf(" ");
                String nm = newnm.substring(0,ind)+"."+newnm.substring(ind+1);
                String addr = address.getText().toString().trim();
                String date = dob.getText().toString().trim();
                String ph = phone.getText().toString().trim();
                String em = email.getText().toString().trim();
                String usr = username.getText().toString().trim();
                String psw = password.getText().toString().trim();
                String bloodg = blood.getText().toString().trim() ;

                Log.e("Name","Name="+nm);

                String message = nm+","+addr+","+date+","+ph+","+em+","+usr+","+psw+","+bloodg;

                if(valid==1) {
                    new RegisterUser(getApplicationContext()).execute(message);

                }
            }
        });

    }

}
