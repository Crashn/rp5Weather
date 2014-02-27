package com.nikitin.parser;

import android.util.Log;
import android.util.Xml;
import com.nikitin.WhatWeather.WeatherDay;
import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

public class XmlPullWeatherParser extends BaseWeatherParser {

    public XmlPullWeatherParser(String weatherUrl){
        super(weatherUrl);
    }

    @Override
    public List<WeatherDay> parse() {

        List <WeatherDay> weatherDays = null;
        XmlPullParser parser = Xml.newPullParser();

        try{
            parser.setInput(this.getInputStream(),null);
            int eventType = parser.getEventType();

            WeatherDay currentDay = null;
            boolean done = false;

            while (eventType != XmlPullParser.END_DOCUMENT && !done){
                String name ;

                switch (eventType){
                    case XmlPullParser.START_DOCUMENT:
                        weatherDays = new ArrayList<WeatherDay>();
                        break;
                    case XmlPullParser.START_TAG:
                        name = parser.getName();

                        if(name.equalsIgnoreCase(ITEM)){
                            currentDay = new WeatherDay();
                        } else if(currentDay != null){

                            if(name.equalsIgnoreCase(DATE_TIME)){
                                currentDay.setDay(parser.nextText());
                            }
                            else if(name.equalsIgnoreCase(HOUR_TIME)){
                                currentDay.setTime(Integer.parseInt( parser.nextText()));
                            }
                            else if(name.equalsIgnoreCase(CLOUD_COVER)){
                                currentDay.setCloudCover(Integer.parseInt(parser.nextText()));
                            }
                            else if(name.equalsIgnoreCase(PRESSURE)){
                                currentDay.setPressure(Integer.parseInt(parser.nextText()));
                            }
                            else if(name.equalsIgnoreCase(TEMPERATURE)){
                                currentDay.setTemp(Double.parseDouble( parser.nextText()));
                            }
                            else if(name.equalsIgnoreCase(HUMIDITY)){
                                currentDay.setHumidity(Integer.parseInt( parser.nextText()));
                            }
                            else if(name.equalsIgnoreCase(WIND_DIRECTION)){
                                currentDay.setWindDirection(parser.nextText());
                            }
                            else if(name.equalsIgnoreCase(WIND_VELOCITY)){
                                currentDay.setWindVelocity(Double.parseDouble( parser.nextText()));
                            }
                            else if(name.equalsIgnoreCase(FALLS)){
                                currentDay.setFalls(Double.parseDouble( parser.nextText()));
                            }
                            else if(name.equalsIgnoreCase(DROPS)){
                                currentDay.setDrops(Double.parseDouble( parser.nextText()));
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        name = parser.getName();
                        if(name.equalsIgnoreCase(ITEM) && currentDay != null){
                            weatherDays.add(currentDay);
                        }
                        else if( name.equalsIgnoreCase(END)){
                            done = true;
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e){
            //throw new RuntimeException(e);
            Log.e("WEATHER_PARSER","CANT PARSE XML");
        }
        return weatherDays;
    }

}
