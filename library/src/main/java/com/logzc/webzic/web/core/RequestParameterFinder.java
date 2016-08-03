package com.logzc.webzic.web.core;

import com.logzc.webzic.annotation.RequestParam;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Load parameter from @RequestParam
 * if parameter has no @RequestParam, return null;
 * Created by lishuang on 2016/7/29.
 */
public class RequestParameterFinder {

    //only cache a class's info.
    private Map<Method, List<RequestParam>> methodCache = new HashMap<>();


    public List<RequestParam> get(Method method) {


        //check cache.
        if (methodCache.containsKey(method)) {
            return methodCache.get(method);
        }

        //no cache. Fill cache.
        findAllMethodParameters(method.getDeclaringClass());

        return methodCache.get(method);


    }

    public void findAllMethodParameters(Class clazz) {


        //methodCache.clear();

        Method[] methods = clazz.getDeclaredMethods();

        if (methods.length == 0) {

            return;
        }

        for (Method method : methods) {

            Parameter[] parameters = method.getParameters();

            List<RequestParam> requestParams = new ArrayList<>(parameters.length);

            for (Parameter parameter : parameters) {

                if (parameter.isAnnotationPresent(RequestParam.class)) {

                    RequestParam requestParam = parameter.getAnnotation(RequestParam.class);
                    requestParams.add(requestParam);

                } else {
                    requestParams.add(null);
                }
            }

            methodCache.put(method, requestParams);


        }


    }

}
