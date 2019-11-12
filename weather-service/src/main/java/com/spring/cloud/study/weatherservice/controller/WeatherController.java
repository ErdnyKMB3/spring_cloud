package com.spring.cloud.study.weatherservice.controller;

import com.spring.cloud.study.weatherservice.model.Weather;
import com.spring.cloud.study.weatherservice.service.WeatherServcie;
import org.apache.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.CredentialException;


@RestController
public class WeatherController {


    private WeatherServcie service;

    @Autowired
    public WeatherController(WeatherServcie service) {
        this.service = service;
    }

    @PostMapping("/getWeather")
    public String getWeather(@RequestParam(required = true) String city) throws CredentialException {
        if (city == null) {
            throw new CredentialException("invalid city");
        }
        Weather weather = new Weather();
        try{
            weather = service.getWeather(city);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weather.toString();
    }

    @GetMapping("/greeting")
    public String hello() {
        return "greeting";
    }
}
