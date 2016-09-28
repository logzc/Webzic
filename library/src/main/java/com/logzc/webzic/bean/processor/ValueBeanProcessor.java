package com.logzc.webzic.bean.processor;

/**
 * process @Value annotation for beans.
 * Created by lishuang on 2016/9/28.
 */
public class ValueBeanProcessor implements BeanProcessor {


    @Override
    public Object beforeInit(Object bean, String beanName) {
        return bean;
    }

    @Override
    public Object afterInit(Object bean, String beanName) {


        //TODO: here needs to be finished.

        return null;
    }
}
