package com.logzc.webzic.bean.processor;

import com.logzc.webzic.annotation.Repository;
import com.logzc.webzic.annotation.Value;
import com.logzc.webzic.bean.AppContext;
import com.logzc.webzic.converter.TypeDescriptor;
import com.logzc.webzic.util.PropertyUtil;

import java.lang.reflect.Field;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * process @Value annotation for beans.
 * Created by lishuang on 2016/9/28.
 */
public class RepositoryBeanProcessor implements BeanProcessor {

    private static Properties properties = null;

    @Override
    public void beforeInit(Object bean, Class<?> clazz) throws Exception {
        if (properties == null) {
            properties = PropertyUtil.loadProperties("/config.properties");
        }


        //Only handle class with @Repository.
        if(!clazz.isAnnotationPresent(Repository.class)){
            return;
        }

        //instance the clazz.
    }

    @Override
    public void afterInit(Object bean, Class<?> clazz) throws Exception {



    }
}
