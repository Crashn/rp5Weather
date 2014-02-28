package com.nikitin.WhatWeather;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.util.Log;
import com.nikitin.parser.XmlPullWeatherParser;

public class WeatherModel implements ModelWeatherInterface {

    static final int TODAY_DATE_TIME = 1;
    static final int TOMORROW_DATE_TIME = 2;
    static final int AFTER_TOMORROW_DATE_TIME = 3;

    private List<ObserverWeatherInterface> observers;

    private List<WeatherDay> allDaysData;
    private List<WeatherDay> todayDayData;
    private List<WeatherDay> tomorrowDayData;
    private List<WeatherDay> afterTomorrowDayData;

    private String cityUrl="http://rp5.ru/xml/6036/00000/ru"; //default url (NSK)

    public WeatherModel(){
        observers = new ArrayList<ObserverWeatherInterface>();
        allDaysData = new ArrayList<WeatherDay>();
        todayDayData = new ArrayList<WeatherDay>();
        tomorrowDayData = new ArrayList<WeatherDay>();
        afterTomorrowDayData = new ArrayList<WeatherDay>();

    }
    WeatherModel(String cityUrl){
        this();
        this.cityUrl = cityUrl;

    }

        @Override
    public void addObserver(ObserverWeatherInterface o) {
            observers.add(o);

    }

    @Override
    public void delObserver(ObserverWeatherInterface o) {
        if(!observers.isEmpty()){
            observers.remove(o);
        }

    }

    @Override
    public void notifyObservers() {

        for(ObserverWeatherInterface obs : observers){

            obs.update();

        }

    }

    @Override
    public void collectData() {

        allDaysData = new ArrayList<WeatherDay>();

        getForecast();

    }

    private void getForecast(){

        try{
        XmlPullWeatherParser parser = new XmlPullWeatherParser(cityUrl);

        allDaysData = parser.parse();   //get all forecast


        if(!allDaysData.isEmpty()){
            divideData();  //divide forecast for days
            notifyObservers();
        }
        else
            Log.e("MODEL","CANT GET DATA");
        }catch (Exception e){

        }

    }

    public List<WeatherDay> getAllDaysData(){
        return allDaysData;
    }

    public List<WeatherDay> getTodayDayData() {
        return todayDayData;
    }

    public List<WeatherDay> getTomorrowDayData() {
        return tomorrowDayData;
    }

    public List<WeatherDay> getAfterTomorrowDayData() {
        return afterTomorrowDayData;
    }


    /**
     *
     * @param date - timestamp from xml
     * @return 1- if today is day of timestamp, 2 - tomorrow, 3 - day after tomorrow, 4 - something going wrong
     *
     */

    private int whatDate(String date)  {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,0);

        Date dateNow = null;
        try {
            dateNow = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE,1);

        Calendar cal3 = Calendar.getInstance();
        cal3.add(Calendar.DATE, 2);

        String today = dateFormat.format(cal.getTime());
        String dayToCheck = dateFormat.format(dateNow);
        String tomorrow = dateFormat.format(cal2.getTime());
        String afterTomorrow = dateFormat.format(cal3.getTime());



        if(dayToCheck.equals(today)){

            return TODAY_DATE_TIME;
        }
        else if(dayToCheck.equals(tomorrow)){

            return TOMORROW_DATE_TIME;
        }
        else if(dayToCheck.equals(afterTomorrow)){

            return AFTER_TOMORROW_DATE_TIME;
        }
        else{

        }
        return 4;

    }

    /**
     * divide today, tomorrow, day after tomorrow data
     *
     * List<WeatherDay> allDaysData;
     *
     * List<WeatherDay> todayDayData;
     * List<WeatherDay> tomorrowDayData;
     * List<WeatherDay> afterTomorrowDayData;
     */

    private void divideData(){

        for(WeatherDay weatherDay: allDaysData){

            switch (whatDate(weatherDay.getDay())){

                case TODAY_DATE_TIME:
                    todayDayData.add(weatherDay);
                    break;
                case TOMORROW_DATE_TIME:
                    tomorrowDayData.add(weatherDay);
                    break;
                case AFTER_TOMORROW_DATE_TIME:
                    afterTomorrowDayData.add(weatherDay);
                    break;
                default:
                    break;
            }
        }
    }


}
