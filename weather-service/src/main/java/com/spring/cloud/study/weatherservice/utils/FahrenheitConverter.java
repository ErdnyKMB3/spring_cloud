package com.spring.cloud.study.weatherservice.utils;



class FahrenheitConverter {

    static double convertToCesium(double value) {
        return Math.round((value - 32) / 1.8);
    }
}
