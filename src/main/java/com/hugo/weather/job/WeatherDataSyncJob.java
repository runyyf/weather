package com.hugo.weather.job;

import com.hugo.weather.service.CityDataService;
import com.hugo.weather.service.WeatherDataService;
import com.hugo.weather.utils.SpringToolUtils;
import com.hugo.weather.vo.City;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;


public class WeatherDataSyncJob extends QuartzJobBean{
    private static final Logger logger = LoggerFactory.getLogger(WeatherDataSyncJob.class);


    private CityDataService cityDataService;
    private WeatherDataService weatherDataService;

    public WeatherDataSyncJob() {
        initService();
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            List<City> cityList = cityDataService.listCity();
            for (City city:cityList){
                weatherDataService.syncWeatherDataByCityId(city.getCityId());
            }
        } catch (Exception e) {
            logger.error("error"+e);
            e.printStackTrace();
        }

    }

    private void initService(){
        cityDataService = (CityDataService) SpringToolUtils.getBean("cityDataServiceImpl");
        weatherDataService = (WeatherDataService) SpringToolUtils.getBean("weatherDataServiceImpl");
    }
}
