package com.hugo.weather.service;

import com.hugo.weather.vo.Weather;

/**
 * @Author: yuyf
 * @Description:
 * @Date: Created in 14:27 2018/9/30
 */
public interface WeatherReportService {
    /**
     * @Author: yuyf
     * @Description: 根据城市id获取天气数据
     * @Date: Created in 14:28 2018/9/30
    */
    Weather getDataByCityId(String cityId);
}
