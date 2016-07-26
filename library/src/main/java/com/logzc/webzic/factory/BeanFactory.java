package com.logzc.webzic.factory;

/**
 * Created by lishuang on 2016/7/19.
 */
public interface BeanFactory {


    <T> T getBean(Class<T> clazz);



}
