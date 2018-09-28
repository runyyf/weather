package com.hugo.weather.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hugo.weather.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class WeatherDataServiceImpl implements WeatherDataService {
    private static final String WEATHER_URL = "http://wthrcdn.etouch.cn/weather_mini?";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public WeatherResponse getDataByCityId(String cityId) {
        String url = WEATHER_URL+"citykey="+cityId;
        return doGetWeather(url);
    }

    @Override
    public WeatherResponse getDataByCityName(String cityName) {
        String url = WEATHER_URL+"city="+cityName;
        return doGetWeather(url);
    }

    private WeatherResponse doGetWeather(String url){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,String.class);

        ObjectMapper mapper =new ObjectMapper();
        WeatherResponse resp =null;
        String strBody = null;

        if (responseEntity.getStatusCodeValue() == 200){
            strBody = responseEntity.getBody();
        }else {
            return null;
        }

        try {
           resp = mapper.readValue(strBody,WeatherResponse.class);
        }catch (IOException e){
            e.printStackTrace();
        }

        return resp;
    }
}
