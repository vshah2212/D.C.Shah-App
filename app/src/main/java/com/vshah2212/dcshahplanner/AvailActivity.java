package com.vshah2212.dcshahplanner;


import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;


public class AvailActivity extends Activity
{
    Button b1;
    EditText et1;
    LinearLayout layout;

    public ListView listViewEvents = null;
    public List<Event> Events = null;

    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avail);

        // layout = (RelLayout) findViewById(R.id.Linearlay);

        String[] Eventarr = getIntent().getStringArrayExtra("Events");

        this.listViewEvents = (ListView) this.findViewById(R.id.lv_Events);

        this.Events = new ArrayList<Event>();
        Event Event = null;

        for(int i=0;i<Eventarr.length;i++) {

            if(Eventarr[i]==null)
                break;
            Event = new Event();
            int y=i+1;
            Event.setId(y);
            Event.setName(Eventarr[i]);
            this.Events.add(Event);

           /* Event = new Event();
            Event.setId(2);
            Event.setName("Burger");
            this.Events.add(Event);

            Event = new Event();
            Event.setId(3);
            Event.setName("French Fries");
            this.Events.add(Event);

            Event = new Event();
            Event.setId(4);
            Event.setName("Pasta");
            this.Events.add(Event); */
        }
        EventViewAdapter adapter = new EventViewAdapter(this, this.Events);
        this.listViewEvents.setAdapter(adapter);

        b1 = (Button) findViewById(R.id.Gobtn);
        et1 = (EditText) findViewById(R.id.Num);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = et1.getText().toString();
                if(num.equalsIgnoreCase(""))
                {
                    num="1";
                }
                Intent intent=new Intent(AvailActivity.this,AvailSend.class);
                Bundle extras= new Bundle();
                List<Event> selectedEvent = new ArrayList<Event>();
                int i=1;
                for(Event s: Events)
                {
                    if(s.isSelected()) {
                        selectedEvent.add(s); // Here you will get your selected skill in this list.
                        extras.putString("Item"+i+"",s.getName());
                        i++;
                    }
                }
                intent.putExtras(extras);
                Log.e("Numin1","Num1:"+num);
                intent.putExtra("Number",num);
                startActivity(intent);
            }
        });


    }
}