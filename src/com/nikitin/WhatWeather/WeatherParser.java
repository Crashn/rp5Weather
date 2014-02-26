package com.nikitin.WhatWeather;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


//TODO:переписать тут все

public class WeatherParser extends DefaultHandler {

    final String LOG_TAG = "Weather_Parser";

    List <WeatherDay> list = null;
    List <WeatherDay> myData = null;

    StringBuilder builder;
    WeatherDay day = null;

    @Override
    public void startDocument() throws SAXException {
        list = new ArrayList<WeatherDay>();
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {

        builder = new StringBuilder(); //create StringBuilder to store xml node value

        if(localName.equals("weather")){
            Log.i(LOG_TAG,"=======weather=======");
        }
        else if(localName.equals("point")){
            Log.i(LOG_TAG,"=======point======");
        }
        else if(localName.equals("point_date_time")){
            Log.i(LOG_TAG,"======point_date_time");
        }
        else if(localName.equalsIgnoreCase("timestep")){
            Log.i(LOG_TAG,"=====timestep=====");
            day = new WeatherDay();
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if(localName.equals("timestep")){
            list.add(day);
        }
        else if(localName.equalsIgnoreCase("point_name")){     //тут day еще не создан - ошибка
           // day.setCity("Novosib");
            Log.i(LOG_TAG,"=-====City_======= "+builder.toString());
        }
        else if(localName.equalsIgnoreCase("datetime")){
            day.setDay(builder.toString());
            Log.i(LOG_TAG,"=====date_time"+builder.toString());
        }
        else if(localName.equalsIgnoreCase("temperature")){
            day.setTemp(Double.parseDouble(builder.toString()));
        }
        //Log.i("parse",localName.toString()+"========="+builder.toString());
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        String tempString = new String(ch,start,length);
        builder.append(tempString);
    }

    void startParse() throws IOException, ParserConfigurationException, SAXException {

        //test online file
        URL url = new URL("http://rp5.ru/xml/6036/00000/ru");
        InputStream xml = url.openStream();
        InputStreamReader reader1 = new InputStreamReader(xml);

        String OutputData = "";
        BufferedReader br = new BufferedReader(reader1);
        InputSource is = new InputSource(br);

        WeatherParser parser = new WeatherParser();
        SAXParserFactory factory = SAXParserFactory.newInstance();

        SAXParser sp = factory.newSAXParser();
        XMLReader reader = sp.getXMLReader();
        reader.setContentHandler(parser);
        reader.parse(is);

        myData = parser.list;

        if(myData != null){


            for(WeatherDay xmlRowData : myData){
                if(xmlRowData != null){

                    String pointName = xmlRowData.getCity();
                    String day = xmlRowData.getDay();
                    Double temp = xmlRowData.getTemp();

                    OutputData += "Weather node : \n\n " + pointName + " | " + day + " | " + temp + " \n \n";
                }
            }

        }
        else
            Log.i("WEATHER_ACTIVITY", "********No data to show!!!*************");

        Log.i("WEATHER_ACTIVITY", OutputData);
    }
    List <WeatherDay> getParsedData() throws ParserConfigurationException, SAXException, IOException {
        startParse();
        return myData;
    }
}
