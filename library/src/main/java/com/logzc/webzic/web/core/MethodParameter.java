package com.logzc.webzic.web.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * This is the parameter's info of a method. Mainly get from the method's annotation.
 * This MethodParameter may be a return type.
 * Created by lishuang on 2016/7/25.
 */
public class MethodParameter {

    private final Method method;
    private final Constructor<?> constructor;

    //when index < 0 means Return type.
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


    public MethodParameter(Method method, int parameterIndex) {
        this.method = method;
        this.constructor = null;
        this.parameterIndex = parameterIndex;
    }

    public MethodParameter(Constructor<?> constructor, int parameterIndex) {
        this.method = null;
        this.constructor = constructor;
        this.parameterIndex = parameterIndex;
    }

    public Member getMember() {
        if (this.method != null) {
            return this.method;
        } else {
            return this.constructor;
        }
    }

    public Class<?> getParameterType() {

        if (this.parameterType == null) {
            if (this.parameterIndex < 0) {

                this.parameterType = (this.method != null ? this.method.getReturnType() : null);

            } else {

                if (this.method != null) {
                    this.parameterType = this.method.getParameterTypes()[this.parameterIndex];
                }
                
                if (this.constructor != null) {
                    this.parameterType = this.constructor.getParameterTypes()[this.parameterIndex];
                }
            }
        }
        return this.parameterType;

    }
}
