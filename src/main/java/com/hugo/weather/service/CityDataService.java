package com.hugo.weather.service;

import com.hugo.weather.vo.City;

import java.util.List;

/**
 * @Author: yuyf
 * @Description:
 * @Date: Created in 14:17 2018/9/29
 */
public interface CityDataService  {

    /**
     * @Author: yuyf
     * @Description: 获取城市列表
     * @Date: Created in 14:17 2018/9/29
    */

    public List<City> listCity() throws Exception;

}
