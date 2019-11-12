package com.spring.cloud.study.weatherservice.service.impl;


import com.spring.cloud.study.weatherservice.model.Weather;
import com.spring.cloud.study.weatherservice.service.WeatherServcie;
import com.spring.cloud.study.weatherservice.utils.MyJsonParser;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@NoArgsConstructor
public class WeatherServiceImpl implements WeatherServcie {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private static final String OPENWEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static final String API_KEY = "&APPID=ba50583bee6ef0c778171599da029d9f";

    @Override
    public Weather getWeather(String city) throws Exception {
        HttpGet request = new HttpGet(buildURL(city));
        log.info("full url is {}", request);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
           log.info("status is {}", response.getStatusLine().toString());
            HttpEntity entity = response.getEntity();
            log.info("response is {}", entity);

            if (entity != null) {
                return MyJsonParser.parse(EntityUtils.toString(entity));
            }
        }
        return null;
    }

    private String buildURL(String city) {
        return (OPENWEATHER_URL + city + API_KEY).trim();
    }
}
