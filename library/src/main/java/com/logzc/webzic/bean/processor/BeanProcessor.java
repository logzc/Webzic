package com.logzc.webzic.bean.processor;

/**
 * Created by lishuang on 2016/9/28.
 */
public interface BeanProcessor {

    //before all beans loaded.
    void beforeInit(Object bean, Class<?> clazz) throws Exception;

    //after all beans loaded.
    void afterInit(Object bean, Class<?> clazz) throws Exception;
}
