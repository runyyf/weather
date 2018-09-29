package com.hugo.weather.utils;

import com.sun.jmx.remote.internal.Unmarshal;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.Reader;
import java.io.StringReader;

/**
 * @Author: yuyf
 * @Description:
 * @Date: Created in 14:10 2018/9/29
 */
public class XmlBuilder {
    /**
     * @Author: yuyf
     * @Description: xml 转化为对象
     * @Date: Created in 14:11 2018/9/29
    */
    public static Object xmlStrToObject(Class<?> clazz ,String xmlStr ) throws Exception{
        Object xmlObject = null;
        Reader reader = null;
        JAXBContext context = JAXBContext.newInstance(clazz);

        //xml 转化为对象
        Unmarshaller unmarshaller = context.createUnmarshaller();

        reader = new StringReader(xmlStr);
        xmlObject = unmarshaller.unmarshal(reader);

        if (null != reader){
            reader.close();
        }

        return  xmlObject;
    }
}
