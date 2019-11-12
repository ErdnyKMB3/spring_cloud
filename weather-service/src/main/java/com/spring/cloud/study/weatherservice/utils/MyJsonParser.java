package com.spring.cloud.study.weatherservice.utils;


import com.spring.cloud.study.weatherservice.model.Weather;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class MyJsonParser {


    private static final String MAIN_BLOCK = "main";
    private static final String WEATHER_BLOCK = "weather";
    private static final String TEMPERATURE = "temp";
    private static final String HUMIDITY = "humidity";
    private static final String WEATHER_DESCRIPTION = "description";
    private static final String MAIN_WEATHER_DESCRIPTION = "main";
    private static final String CITY = "city";


    public static Weather parse(String jsonString) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
        JSONObject mainStats = (JSONObject) jsonObject.get(MAIN_BLOCK);
        JSONArray basicStats = (JSONArray) jsonObject.get(WEATHER_BLOCK)  ;
        Weather weather = new Weather();
        weather.setTemperature
                (FahrenheitConverter.convertToCesium((double)mainStats.get(TEMPERATURE)));
        parseArray(basicStats, weather);
        weather.setHumidity((long)mainStats.get(HUMIDITY));
        weather.setCity((String)jsonObject.get(CITY));
        log.info("Weather object is {}", weather);
        return weather;
    }

    private static void parseArray(JSONArray weatherJSON, Weather weather) {
        for (Object o : weatherJSON) {
            JSONObject innerObject = (JSONObject) o;
            weather.setDescription((String) innerObject.get(WEATHER_DESCRIPTION));
            weather.setMain((String) innerObject.get(MAIN_WEATHER_DESCRIPTION));
        }
    }
}
