package com.logzc.webzic.web;

import com.logzc.webzic.util.Assert;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * A handler method.
 * Created by lishuang on 2016/7/21.
 */
public class HandlerMethod {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Getter
    private final Object bean;

//    private final BeanFactory beanFactory;

    @Getter
    private final Class<?> beanType;


    @Getter
    private final Method method;

//    private final Method bridgedMethod;

//    private final MethodParameter[] parameters;

//    private final HandlerMethod resolvedFromHandlerMethod;


    public HandlerMethod(Object bean,Method method){
        Assert.notNull(bean);
        Assert.notNull(method);

        this.bean=bean;
        this.beanType=bean.getClass();
        this.method=method;

    }
}
