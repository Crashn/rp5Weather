package com.nikitin.rp5Weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.nikitin.WhatWeather.WeatherDay;

import java.util.ArrayList;
import java.util.List;

public class CustomWListAdapter extends BaseAdapter {

    List<WeatherDay> items = new ArrayList<WeatherDay>();
    Context context;

    public CustomWListAdapter(Context context, List<WeatherDay> arrayList) {

        if(arrayList != null)
            this.items = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = LayoutInflater.from(context);
        if(view == null){
            view = inflater.inflate(R.layout.item,viewGroup,false);
        }
        TextView dateTv = (TextView)view.findViewById(R.id.textView);
        TextView dayTimeTv = (TextView)view.findViewById(R.id.textView1);
        TextView tempTv = (TextView)view.findViewById(R.id.textView2);
        TextView humTv = (TextView)view.findViewById(R.id.textView3);
        TextView dropsTv = (TextView)view.findViewById(R.id.textView4);

        dateTv.setText(items.get(i).getDay());
        dayTimeTv.setText(items.get(i).getTime());
        tempTv.setText(items.get(i).getTemp() + " C");
        humTv.setText(items.get(i).getHumidity() + "обл");
        dropsTv.setText(items.get(i).getDrops() + "osadki");


        return view;
    }
}
