package com.nikitin.parser;

import com.nikitin.WhatWeather.WeatherDay;

import java.util.List;

public interface xmlParser {

    List<WeatherDay> parse();
}
