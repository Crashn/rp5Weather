package com.nikitin.rp5Weather;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.nikitin.WhatWeather.*;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity implements ViewWeatherInterface,ObserverWeatherInterface {

    List<WeatherDay> allDayForecast = new ArrayList<WeatherDay>();
    ModelWeatherInterface model;

    ListAdapter adapter = new CustomWListAdapter(this, allDayForecast);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        model = new WeatherModel();
        model.addObserver(this);
        model.collectData();



        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }

    @Override
    public void display() {

    }

    @Override
    public void update() {

        if(model instanceof WeatherModel){
            allDayForecast = ((WeatherModel) model).getAllDaysData();
        }

    }
}
