package com.vshah2212.dcshahplanner;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EventView extends LinearLayout
{
    // To store Event information
    private Event Event;
    // For UI
    private Context context;
    private CheckBox chkSelect;
    private TextView tvEventName;

    public EventView(Context context, Event Event)
    {
        super(context);
        this.context = context;
        this.Event = Event;

        /* Initialize UI components */
        this.chkSelect = new CheckBox(this.context);
        this.chkSelect.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                EventView.this.Event.setSelected(((CheckBox) view).isChecked());
            }
        });

        this.tvEventName = new TextView(this.context);
        this.tvEventName.setTextSize(15);
        this.tvEventName
                .setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        this.tvEventName.setText(" " + this.Event.getName());

        // Add components to main layout
        this.setOrientation(HORIZONTAL);
        this.addView(this.chkSelect, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        this.addView(this.tvEventName, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

    }

    public Event getEvent()
    {
        return Event;
    }

    public void setEvent(Event Event)
    {
        this.Event = Event;
    }
}
