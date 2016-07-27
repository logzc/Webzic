package com.logzc.webzic.factory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * this bean factory contains all the bean needed by the framework.
 * Created by lishuang on 2016/7/26.
 */
public class WidgetBeanFactory extends AbstractBeanFactory {


    @Override
    public void init() {

        //register singletons.
        beanMap.put(ObjectMapper.class, new ObjectMapper());
        classes.add(ObjectMapper.class);

    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getBean(Class<T> clazz) {

        return (T) beanMap.get(clazz);

    }


}
