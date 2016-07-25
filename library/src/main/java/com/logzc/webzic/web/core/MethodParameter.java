package com.logzc.webzic.web.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * This is the parameter's info of a method. Mainly get from the method's annotation.
 * Created by lishuang on 2016/7/25.
 */
public class MethodParameter {

    private final Method method;
    private final Constructor<?> constructor;

    private final int parameterIndex;

    //if the parameter is a list of list. This field is needed.
    //private int nestingLevel = 1;
    //Map<Integer, Integer> typeIndexesPerLevel;


    private Class<?> containingClass;
    private Class<?> parameterType;
    private Type genericParameterType;
    private Annotation[] annotations;
    //private ParameterNameDiscoverer parameterNameDiscoverer;
    private String parameterName;


    public MethodParameter(Method method, Constructor<?> constructor, int parameterIndex) {
        this.method = method;
        this.constructor = constructor;
        this.parameterIndex = parameterIndex;
    }
}
