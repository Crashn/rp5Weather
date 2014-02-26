package com.nikitin.WhatWeather;

public class WeatherDay {
     /**
      * one day in weather forecast
      */
    private String day;
    private String city="Новосибирск"; //переместить в другое место. погода уже по определенному городу собирается.
    private int humidity;
    private int pressure;
    private String windDirection;
    private double windVelocity;
    private double temp;
    private int cloudCover;
    private int time;     //G in xml
    private double falls;
    private double drops;



    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public double getWindVelocity() {
        return windVelocity;
    }

    public void setWindVelocity(double windVelocity) {
        this.windVelocity = windVelocity;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public int getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(int cloudCover) {
        this.cloudCover = cloudCover;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getFalls() {
        return falls;
    }

    public void setFalls(double falls) {
        this.falls = falls;
    }

    public double getDrops() {
        return drops;
    }

    public void setDrops(double drops) {
        this.drops = drops;
    }
}
