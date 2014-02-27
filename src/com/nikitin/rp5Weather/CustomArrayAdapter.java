package com.nikitin.rp5Weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.nikitin.WhatWeather.WeatherDay;

import java.util.List;

public class CustomArrayAdapter extends ArrayAdapter<WeatherDay>{

    private final Context context;
    private final List<WeatherDay> items;

    public CustomArrayAdapter(Context context, int textViewResourceId, List<WeatherDay> objects) {
        super(context, textViewResourceId, objects);

        this.context = context;
        this.items = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.item,parent,false);



        TextView dateTv = (TextView)rowView.findViewById(R.id.textView);
        TextView dayTimeTv = (TextView)rowView.findViewById(R.id.textView1);
        TextView tempTv = (TextView)rowView.findViewById(R.id.textView2);
        TextView humTv = (TextView)rowView.findViewById(R.id.textView3);
        TextView dropsTv = (TextView)rowView.findViewById(R.id.textView4);

        dateTv.setText(items.get(position).getDay());
        dayTimeTv.setText(items.get(position).getTime()+ " часов");
        tempTv.setText(items.get(position).getTemp() + " C");
        humTv.setText(items.get(position).getHumidity() + " обл");
        dropsTv.setText(items.get(position).getDrops() + " osadki");

        return rowView;
    }
}
