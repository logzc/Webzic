package com.logzc.webzic.bean.factory;

/**
 * Created by lishuang on 2016/7/19.
 */
public interface BeanFactory {


    void init();

    <T> T getBean(Class<T> clazz);



}
