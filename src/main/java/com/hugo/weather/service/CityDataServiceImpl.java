package com.hugo.weather.service;

import com.hugo.weather.utils.XmlBuilder;
import com.hugo.weather.vo.City;
import com.hugo.weather.vo.CityList;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @Author: yuyf
 * @Description:
 * @Date: Created in 14:18 2018/9/29
 */
@Service
public class CityDataServiceImpl implements CityDataService {
    @Override
    public List<City> listCity() throws Exception  {
//        读取xml文件
        Resource resource = new ClassPathResource("cityList.xml");
        BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
        StringBuffer buffer =new StringBuffer();
        String line = "";

        while ((line = br.readLine())!=null){
            buffer.append(line);
        }

//        xml文件转化为对象
        CityList cityList  = (CityList)XmlBuilder.xmlStrToObject(CityList.class,buffer.toString());
        return  cityList.getCityList();
    }
}
