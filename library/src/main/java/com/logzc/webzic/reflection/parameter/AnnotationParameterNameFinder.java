package com.logzc.webzic.reflection.parameter;

import com.logzc.webzic.annotation.RequestParam;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Load parameter name from @RequestParam
 * if parameter has no @RequestParam, return "";
 * Created by lishuang on 2016/7/29.
 */
public class AnnotationParameterNameFinder implements ParameterNameFinder {

    //only cache a class's info.
    private Map<Method, List<String>> methodCache = new HashMap<>();


    @Override
    public List<String> get(Method method) {


        //check cache.
        if (methodCache.containsKey(method)) {
            return methodCache.get(method);
        }

        //no cache. Fill cache.
        findAllMethodParameters(method.getDeclaringClass());

        return methodCache.get(method);


    }

    //no constructor should appear here.
    @Override
    public List<String> get(Constructor constructor) {


        return null;
    }

    public void findAllMethodParameters(Class clazz) {


        //methodCache.clear();

        Method[] methods = clazz.getDeclaredMethods();

        if (methods.length == 0) {

            return;
        }

        for (Method method : methods) {

            Parameter[] parameters = method.getParameters();

            List<String> parameterNames = new ArrayList<>(parameters.length);

            for (Parameter parameter : parameters) {

                if (parameter.isAnnotationPresent(RequestParam.class)) {

                    RequestParam requestParam = parameter.getAnnotation(RequestParam.class);
                    parameterNames.add(requestParam.name());

                } else {
                    parameterNames.add("");
                }
            }

            methodCache.put(method, parameterNames);


        }


    }

}
