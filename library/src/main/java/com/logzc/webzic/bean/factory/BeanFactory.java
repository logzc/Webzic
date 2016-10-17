package com.logzc.webzic.bean.factory;

import java.util.Map;

/**
 * Created by lishuang on 2016/7/19.
 */
public interface BeanFactory {


    void init();

    <T> T getBean(Class<T> clazz);

    Map<Class<?>, Object> getBeanMap();

}
