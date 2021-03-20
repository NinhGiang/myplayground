package com.example.festbox;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class EventCardAdapter extends ArrayAdapter<EventCard> {
    private ArrayList<EventCard> eventCards;
    private Context mContext;
    private TextView textView_title, textView_startTime, textView_address, textView_availability;
    private ImageView imageView;

    public EventCardAdapter(ArrayList<EventCard> data, Context context, int resourceId) {
        super(context, resourceId, data);
        this.eventCards = data;
        this.mContext = context;
    }

    public EventCardAdapter(ArrayList<EventCard> data, Context context) {
        super(context, android.R.layout.simple_list_item_2, data);
        this.eventCards = data;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return eventCards.size();
    }

    @Override
    public EventCard getItem(int position) {
        return eventCards.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EventCard eventCard = (EventCard) getItem(position);
        convertView = LayoutInflater.from(mContext).inflate(R.layout.event_card, parent, false);

        textView_title = convertView.findViewById(R.id.event_title);
        textView_startTime = convertView.findViewById(R.id.start_time);
        textView_address = convertView.findViewById(R.id.address);
        textView_availability = convertView.findViewById(R.id.available);
        imageView = convertView.findViewById(R.id.event_image);

        textView_title.setText(eventCard.getTitle());
        textView_startTime.setText(eventCard.getStartTime());
        textView_address.setText(eventCard.getAddress());
        textView_availability.setText(eventCard.isAvailable()? "Available":"Sold out");
        new ImageLoadTask(eventCard.getImgUrl(), imageView).execute();

        return convertView;
    }
    public void update(ArrayList<EventCard> eventCards) {
        this.eventCards = eventCards;
        notifyDataSetChanged();
    }
}
