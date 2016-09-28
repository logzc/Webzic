package com.logzc.webzic.web.core;

import com.logzc.webzic.annotation.RequestParam;
import com.logzc.webzic.bean.factory.AppContext;
import com.logzc.webzic.bean.factory.WidgetBeanFactory;
import com.logzc.webzic.reflection.parameter.AsmParameterNameFinder;
import com.logzc.webzic.reflection.parameter.ParameterNameFinder;
import com.logzc.webzic.util.Assert;
import lombok.Getter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * This is the parameter's info of a method. Mainly get from the method's annotation.
 * This MethodParameter may be a return type.
 * Created by lishuang on 2016/7/25.
 */
public class MethodParameter {

    private final Method method;
    private final Constructor<?> constructor;

    //when index < 0 means Return type.
    @Getter
    private final int parameterIndex;

    //if the parameter is a list of list. This field is needed.
    private int nestingLevel = 1;
    Map<Integer, Integer> typeIndexesPerLevel;


    private Class<?> containingClass;
    private Class<?> parameterType;
    private Type genericParameterType;
    private Annotation[] parameterAnnotations;

    private ParameterNameFinder parameterNameFinder;

    private String parameterName;
    //whether this parameter is required.
    @Getter
    private boolean required;


    public MethodParameter(Method method, int parameterIndex) {
        this(method, parameterIndex, 1);
    }

    /**
     * @param method         method
     * @param parameterIndex index of the parameter.
     * @param nestingLevel   the dimension of the List. Default not list parameter.
     */
    public MethodParameter(Method method, int parameterIndex, int nestingLevel) {
        Assert.notNull(method, "Method must not be null.");
        this.method = method;
        this.parameterIndex = parameterIndex;
        this.nestingLevel = nestingLevel;
        this.constructor = null;


        initParameterInfo();
    }

    public MethodParameter(Constructor<?> constructor, int parameterIndex) {
        this(constructor, parameterIndex, 1);
    }

    /**
     * @param constructor    method
     * @param parameterIndex index of the parameter.
     * @param nestingLevel   the dimension of the List. Default not list parameter.
     */
    public MethodParameter(Constructor<?> constructor, int parameterIndex, int nestingLevel) {
        Assert.notNull(constructor, "Method must not be null.");
        this.constructor = constructor;
        this.parameterIndex = parameterIndex;
        this.nestingLevel = nestingLevel;
        this.method = null;


        //not support Constructor's parameterName yet.
        //initParameterInfo();
    }

    public Method getMethod() {
        return this.method;
    }

    public Constructor<?> getConstructor() {
        return this.constructor;
    }

    public Member getMember() {
        if (this.method != null) {
            return this.method;
        } else {
            return this.constructor;
        }
    }


    public Class<?> getParameterType() {

        //lazy load
        if (this.parameterType == null) {

            //return type. constructor no return type.
            if (this.parameterIndex < 0) {

                this.parameterType = (this.method != null ? this.method.getReturnType() : null);

            } else {

                if (this.method == null) {
                    this.parameterType = this.constructor.getParameterTypes()[this.parameterIndex];
                } else {
                    this.parameterType = this.method.getParameterTypes()[this.parameterIndex];
                }
            }
        }
        return this.parameterType;
    }

    public Type getGenericParameterType() {
        if (this.genericParameterType == null) {
            if (this.parameterIndex < 0) {
                if (this.method != null) {
                    this.genericParameterType = this.method.getGenericReturnType();
                } else {
                    //impossible case.
                    this.genericParameterType = null;
                }
            } else {
                if (this.method != null) {
                    this.genericParameterType = this.method.getGenericParameterTypes()[this.parameterIndex];
                } else {
                    this.genericParameterType = this.constructor.getGenericParameterTypes()[this.parameterIndex];
                }
            }
        }
        return this.genericParameterType;
    }

    public Annotation[] getParameterAnnotations() {

        if (this.parameterAnnotations == null) {
            Annotation[][] annotationArray = this.method != null ?
                    this.method.getParameterAnnotations() :
                    this.constructor.getParameterAnnotations();

            if (parameterIndex >= 0 && this.parameterIndex < annotationArray.length) {
                this.parameterAnnotations = annotationArray[parameterIndex];
            } else {
                this.parameterAnnotations = new Annotation[0];
            }
        }

        return this.parameterAnnotations;
    }

    public Annotation[] getMethodAnnotations() {

        if (this.method != null) {
            return this.method.getAnnotations();
        } else {
            return this.constructor.getAnnotations();
        }
    }


    public Class<?> getDeclaringClass() {
        return getMember().getDeclaringClass();
    }


    public String getParameterName() {
        return parameterName;
    }

    //mainly get parameter name and parameter's require.1
    public void initParameterInfo() {


        //return type. No need go on.
        if (parameterIndex < 0) {
            return;
        }


        //for test purpose.

        RequestParameterFinder requestParameterFinder;
        AsmParameterNameFinder asmParameterNameFinder;

        WidgetBeanFactory widgetBeanFactory = AppContext.getBeanFactory(WidgetBeanFactory.class);


        //get the parameter name from two finders.
        requestParameterFinder = widgetBeanFactory.getBean(RequestParameterFinder.class);
        asmParameterNameFinder = widgetBeanFactory.getBean(AsmParameterNameFinder.class);

        if (requestParameterFinder == null) {
            requestParameterFinder = new RequestParameterFinder();
        }
        if (asmParameterNameFinder == null) {
            asmParameterNameFinder = new AsmParameterNameFinder();
        }


        List<RequestParam> requestParams = requestParameterFinder.get(this.method);
        RequestParam requestParam = requestParams.get(parameterIndex);

        if (requestParam != null) {

            parameterName = requestParam.name();
            required = requestParam.required();
        } else {
            List<String> parameterNames = asmParameterNameFinder.get(this.method);
            parameterName = parameterNames.get(parameterIndex);
            required = true;
        }
    }


}
