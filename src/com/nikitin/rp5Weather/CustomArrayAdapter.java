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
        TextView fallsTv = (TextView)rowView.findViewById(R.id.textView4);

        dateTv.setText(items.get(position).getDay());
        dayTimeTv.setText(items.get(position).getTime()+ " часов");
        tempTv.setText(items.get(position).getTemp() + " C, ");
        humTv.setText(kindOfCloud(items.get(position).getCloudCover()) + ", ");
        fallsTv.setText(kindOfFalls(items.get(position).getFalls(), items.get(position).getDrops()));

        return rowView;
    }

    /**
     *
     * @param falls  kind of falls
     * @param drops  power of falls
     */

    private String kindOfFalls(double falls, double drops){
        String fallsKind="Возможны осадки";  //by default

        if(falls == 0){
            fallsKind = "Без осадков";
        }
        else if(falls == 1){
            fallsKind = "Дождь";
        }
        else if(falls == 2){
            fallsKind = "Дождь со снегом";
        }
        else if(falls == 3){
            fallsKind = "Снег";
        }
        return fallsKind;
    }

    private String  kindOfCloud(int cloudCover){

        String kindOfCloud="Ясно";

        if(cloudCover < 20){
            kindOfCloud = "Ясно";
        }
        else if(cloudCover > 20 && cloudCover < 50){
            kindOfCloud = "Малооблачно";
        }
        else if(cloudCover > 50){
            kindOfCloud = "Пасмурно";
        }

        return kindOfCloud;

    }
}
