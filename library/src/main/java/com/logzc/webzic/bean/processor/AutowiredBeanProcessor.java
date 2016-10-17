package com.logzc.webzic.bean.processor;

import com.logzc.webzic.annotation.Autowired;
import com.logzc.webzic.annotation.Repository;
import com.logzc.webzic.bean.AppContext;
import com.logzc.webzic.util.PropertyUtil;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * process @Value annotation for beans.
 * Created by lishuang on 2016/9/28.
 */
public class AutowiredBeanProcessor implements BeanProcessor {

    private static Properties properties = null;

    @Override
    public void beforeInit(Object bean, Class<?> clazz) throws Exception {

    }

    @Override
    public void afterInit(Object bean, Class<?> clazz) throws Exception {


    }

    @Override
    public void process(Object bean, Class<?> clazz) throws Exception {

        if (properties == null) {
            properties = PropertyUtil.loadProperties("/config.properties");
        }

        //Only inject into fields. NOTE: Exclude inherit fields.
        Field[] declaredFields=clazz.getDeclaredFields();

        for (Field field:declaredFields){
            if(field.isAnnotationPresent(Autowired.class)){

                Object obj= AppContext.getBean(field.getType());


                field.setAccessible(true);
                field.set(bean, obj);

            }
        }


    }
}
