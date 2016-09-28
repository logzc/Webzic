package com.logzc.webzic.bean.processor;

/**
 * Created by lishuang on 2016/9/28.
 */
public interface BeanProcessor {

    Object beforeInit(Object bean, String beanName);

    Object afterInit(Object bean, String beanName);
}
