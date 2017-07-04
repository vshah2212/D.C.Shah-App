package com.vshah2212.dcshahplanner;

import java.util.List;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class EventViewAdapter extends BaseAdapter
{
    private Context context = null;
    private List<Event> EventList = null;

    public EventViewAdapter(Context context, List<Event> EventList)
    {
        this.context = context;
        this.EventList = EventList;
    }

    public int getCount()
    {
        return this.EventList != null ? this.EventList.size() : 0;
    }

    public Object getItem(int position)
    {
        return this.EventList != null ? this.EventList.get(position) : null;
    }

    public long getItemId(int position)
    {
        if (this.EventList != null)
        {
            Event p = this.EventList.get(position);
            return p.getId();
        }
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        EventView EventView;

        if (convertView == null)
        {
            EventView = new EventView(this.context, this.EventList.get(position));
        } else
        {
            EventView = (EventView) convertView;
            EventView.setEvent(this.EventList.get(position));
        }
        return EventView;
    }

    public List<Event> getProductList()
    {
        return this.EventList;
    }
}
