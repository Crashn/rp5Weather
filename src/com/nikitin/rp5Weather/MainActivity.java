package com.nikitin.rp5Weather;


import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.nikitin.WhatWeather.*;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity implements ViewWeatherInterface,ObserverWeatherInterface {

    List<WeatherDay> allDayForecast = new ArrayList<WeatherDay>();
    ModelWeatherInterface model;

    TextView nodata;
    Button updateButton;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //initGui
        nodata = (TextView)findViewById(R.id.dataTv);
        updateButton = (Button)findViewById(R.id.button);

        model = new WeatherModel();
        model.addObserver(this);

        //catch screen rotate
        if(allDayForecast.isEmpty()){
            Object object =  getLastNonConfigurationInstance();

            if(object !=null && object instanceof ArrayList){

                allDayForecast = (ArrayList<WeatherDay>)object;
                nodata.setVisibility(View.GONE);
                display();
            }
        }

        View.OnClickListener onUpdateBntListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nodata.setVisibility(View.GONE);


               TakeDataTask td = new TakeDataTask();
                td.execute();


            }
        };

        updateButton.setOnClickListener(onUpdateBntListener);



    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        return allDayForecast;
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        Intent intent = new Intent("com.nikitin.intent.action.showSpec");

        WeatherDay item = (WeatherDay) getListAdapter().getItem(position);

        intent.putExtra("day",item.getDay());
        intent.putExtra("humidity", item.getHumidity());
        intent.putExtra("temp",item.getTemp());
        intent.putExtra("dayTime", item.getTime());
        intent.putExtra("windDirection",item.getWindDirection());
        intent.putExtra("windVelocity", item.getWindVelocity());
        intent.putExtra("cloud", item.getCloudCover());
        intent.putExtra("falls", item.getFalls());
        intent.putExtra("drops", item.getDrops());
        intent.putExtra("pressure", item.getPressure());

        startActivity(intent);
    }

    @Override
    public void display() {
        ArrayAdapter<WeatherDay> adapter = new CustomArrayAdapter(this,R.layout.item, allDayForecast);

        setListAdapter(adapter);

    }

    @Override
    public void update() {

        if(model instanceof WeatherModel){
            allDayForecast = ((WeatherModel) model).getAllDaysData();
        }

    }

    class TakeDataTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            updateButton.setText("Обновление ....");
        }

        @Override
        protected Void doInBackground(Void... voids) {

            allDayForecast = new ArrayList<WeatherDay>();

            model.collectData();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            updateButton.setText(R.string.updateBtn);

            if(allDayForecast.isEmpty()){
                nodata.setVisibility(View.VISIBLE);
                nodata.setText("Ошибка получения данных");
            }
            else{
                display();
            }
        }
    }


}
