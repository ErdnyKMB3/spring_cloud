package com.ha.zone.simple_service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@SpringBootApplication
@EnableDiscoveryClient
public class SimpleServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleServiceApplication.class, args);
    }

    @RestController
    static
    class MyController {

        @Value("${eureka.instance.metadataMap.zone}")
        private String zone;


        @GetMapping(value = "/weather")
        public String getWeather(@RequestParam String city,
                                 @RequestParam(required = false,
                                         defaultValue = "") String cityCode, Model m) {
            m.addAttribute("city", city);
            m.addAttribute("code", cityCode);
            return "weather";
        }

        @GetMapping(value = "/zone")
        @ResponseBody
        public String zone() {
            return "{\"zone\"=\"" + zone + "\"}";
        }
    }
}
