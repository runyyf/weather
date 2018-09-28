package com.hugo.weather.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hugo.weather.vo.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class WeatherDataServiceImpl implements WeatherDataService {
    private static final Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);
    private static final String WEATHER_URL = "http://wthrcdn.etouch.cn/weather_mini?";
    private static final long TIME_OUT = 1800L;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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
//        如果缓存中有数据，则使用缓存
        String key = url;
        String strBody = null;
        ObjectMapper mapper =new ObjectMapper();
        WeatherResponse resp =null;

        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        if (stringRedisTemplate.hasKey(key)){
            strBody = ops.get(key);
            logger.info("内存中存在"+key);
        }else {
            //        如果缓存中没有数据，则调用接口请求
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,String.class);
            if (responseEntity.getStatusCodeValue() == 200){
                strBody = responseEntity.getBody();
                logger.info("内存中不存在"+key);
                ops.set(url,strBody,TIME_OUT, TimeUnit.SECONDS);
            }else {
                return null;
            }
        }
        try {
           resp = mapper.readValue(strBody,WeatherResponse.class);
        }catch (IOException e){
            logger.info("error!"+e);
        }

        return resp;
    }
}
