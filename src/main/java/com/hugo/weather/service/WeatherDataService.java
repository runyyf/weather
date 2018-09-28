package com.hugo.weather.service;

import com.hugo.weather.vo.Weather;
import com.hugo.weather.vo.WeatherResponse;


public interface WeatherDataService {
    /**
     *
     * 功能描述: 根据城市id查询天气
     *
     * @param: cityId
     * @return: 
     * @auther: yyf
     * @date: 2018/9/28 23:18
     */
    WeatherResponse getDataByCityId(String cityId);

    /**
     *
     * 功能描述: 根据城市Name查询天气
     *
     * @param: cityId
     * @return:
     * @auther: yyf
     * @date: 2018/9/28 23:18
     */
    WeatherResponse getDataByCityName(String cityName);
    
}
