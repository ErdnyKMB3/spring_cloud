package com.spring.cloud.study.weatherservice.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class Weather {

     private String city;
     private long humidity;
     private String main;
     private String description;
     private double temperature;

    @Override
    public String toString() {
        return "Temperature in city " + city + " is equals: " + temperature +
                "\n" + "Main description: " + main +
                "\n" + "some additional info \n[humidity=" + humidity + ",\n" + description + "]";
    }
}
