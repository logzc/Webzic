package com.logzc.webzic.bean.processor;

import com.logzc.webzic.annotation.Repository;
import com.logzc.common.util.PropertyUtil;

import java.util.Properties;

/**
 * process @Value annotation for beans.
 * Created by lishuang on 2016/9/28.
 */
public class RepositoryBeanProcessor implements BeanProcessor {

    private static Properties properties = null;
    @Override
    public void process(Object bean, Class<?> clazz) throws Exception {
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
    public void beforeInit(Object bean, Class<?> clazz) throws Exception {

    }

    @Override
    public void afterInit(Object bean, Class<?> clazz) throws Exception {



    }
}
