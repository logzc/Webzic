package com.logzc.webzic.bean.processor;

import com.logzc.webzic.annotation.Value;
import com.logzc.webzic.util.PropertyUtil;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * process @Value annotation for beans.
 * Created by lishuang on 2016/9/28.
 */
public class ValueBeanProcessor implements BeanProcessor {

    private static Properties properties = null;

    @Override
    public Object beforeInit(Object bean, Class<?> clazz) {
        return bean;
    }

    @Override
    public Object afterInit(Object bean, Class<?> clazz) {

        if (properties == null) {
            properties = PropertyUtil.loadProperties("/config.properties");
        }


        //find all the fields with @Value.
        Field[] fields=clazz.getDeclaredFields();

        for (Field field:fields){

            if(field.isAnnotationPresent(Value.class)){



            }
        }


        //TODO: here needs to be finished.

        return null;
    }
}
