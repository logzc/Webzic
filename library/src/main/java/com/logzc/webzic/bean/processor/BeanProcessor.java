package com.logzc.webzic.bean.processor;

/**
 * Created by lishuang on 2016/9/28.
 */
public interface BeanProcessor {

    void beforeInit(Object bean, Class<?> clazz) throws Exception;

    void afterInit(Object bean, Class<?> clazz) throws Exception;
}
