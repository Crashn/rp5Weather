package com.nikitin.parser;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class BaseWeatherParser implements xmlParser {

    //tag name

    static final String ITEM = "timestep";
    static final String DATE_TIME = "datetime"; //<datetime>2014-2-10 19:00</datetime>
    static final String HOUR_TIME = "G"; // 19; 7;
    static final String CLOUD_COVER = "cloud_cover"; //73 %
    static final String PRESSURE = "pressure"; //765mm
    static final String TEMPERATURE = "temperature";
    static final String HUMIDITY = "humidity"; //81 ?
    static final String WIND_DIRECTION = "wind_direction"; // С-З
    static final String WIND_VELOCITY = "wind_velocity"; //2 (m/c)
    static final String FALLS = "falls";
    static final String DROPS = "drops";
    static final String END = "point";

    final URL weatherUrl;

    protected BaseWeatherParser(String weatherUrl) {

        try {
            this.weatherUrl = new URL(weatherUrl);
        } catch (MalformedURLException e) {
            Log.e("WEATHER_PARSER","Connection error");
            throw new RuntimeException(e);

        }
    }

    protected InputStream getInputStream(){

        try{
            return weatherUrl.openConnection().getInputStream();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
