package com.logzc.webzic.web.core;

import com.logzc.webzic.annotation.RequestParam;
import com.logzc.webzic.factory.AppContext;
import com.logzc.webzic.factory.WidgetBeanFactory;
import com.logzc.webzic.reflection.parameter.AsmParameterNameFinder;
import lombok.Getter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

/**
 * This is the parameter's info of a method. Mainly get from the method's annotation.
 * This MethodParameter may be a return type.
 * Created by lishuang on 2016/7/25.
 */
public class MethodParameter {

    private final Method method;

    //when index < 0 means Return type.
    private final int parameterIndex;

    //if the parameter is a list of list. This field is needed.
    //private int nestingLevel = 1;
    //Map<Integer, Integer> typeIndexesPerLevel;


    private Class<?> containingClass;
    private Class<?> parameterType;
    private Type genericParameterType;
    private Annotation[] annotations;
    @Getter
    private String parameterName;
    //whether this parameter is required.
    @Getter
    private boolean required;


    public MethodParameter(Method method, int parameterIndex) {
        this.method = method;
        this.parameterIndex = parameterIndex;

        initParameterInfo();

    }


    public Class<?> getParameterType() {

        if (this.parameterType == null) {
            if (this.parameterIndex < 0) {

                this.parameterType = (this.method != null ? this.method.getReturnType() : null);

            } else {

                this.parameterType = this.method.getParameterTypes()[this.parameterIndex];

            }
        }
        return this.parameterType;

    }


    //mainly get parameter name and parameter's require.1
    public void initParameterInfo() {

        WidgetBeanFactory widgetBeanFactory = AppContext.getBeanFactory(WidgetBeanFactory.class);
        //get the parameter name from two finders.
        RequestParameterFinder requestParameterFinder = widgetBeanFactory.getBean(RequestParameterFinder.class);
        AsmParameterNameFinder asmParameterNameFinder = widgetBeanFactory.getBean(AsmParameterNameFinder.class);

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
