package com.logzc.webzic.web.core;

import com.logzc.webzic.factory.AppContext;
import com.logzc.webzic.factory.WidgetBeanFactory;
import com.logzc.webzic.reflection.parameter.AnnotationParameterNameFinder;
import com.logzc.webzic.reflection.parameter.AsmParameterNameFinder;

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
    private String parameterName;


    public MethodParameter(Method method, int parameterIndex) {
        this.method = method;
        this.parameterIndex = parameterIndex;
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

    public String getParameterName() {

        WidgetBeanFactory widgetBeanFactory = AppContext.getBeanFactory(WidgetBeanFactory.class);


        //get the parameter name from two finders.
        AnnotationParameterNameFinder annotationParameterNameFinder = widgetBeanFactory.getBean(AnnotationParameterNameFinder.class);
        AsmParameterNameFinder asmParameterNameFinder = widgetBeanFactory.getBean(AsmParameterNameFinder.class);


        List<String> annoParameterNames = annotationParameterNameFinder.get(this.method);
        String annoParameterName = annoParameterNames.get(parameterIndex);


        if(annoParameterName!=null && !annoParameterName.equals("")){
            return annoParameterName;
        }else{

            List<String> parameterNames = asmParameterNameFinder.get(this.method);
            return parameterNames.get(parameterIndex);
        }

    }
}
