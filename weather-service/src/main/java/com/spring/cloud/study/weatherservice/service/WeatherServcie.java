package com.spring.cloud.study.weatherservice.service;


import com.spring.cloud.study.weatherservice.model.Weather;

public interface WeatherServcie {

    Weather getWeather(String city) throws Exception;
}
