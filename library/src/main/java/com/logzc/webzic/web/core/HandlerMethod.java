package com.logzc.webzic.web.core;

import com.logzc.webzic.converter.ConversionService;
import com.logzc.webzic.converter.TypeDescriptor;
import com.logzc.webzic.bean.factory.AppContext;
import com.logzc.webzic.util.Assert;
import com.logzc.webzic.util.JsonUtil;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.*;

/**
 * A handler method.
 * Created by lishuang on 2016/7/21.
 */
public class HandlerMethod {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Getter
    private final Object bean;

//    private final AnnotationBeanFactory beanFactory;

    @Getter
    private final Class<?> beanType;


    @Getter
    private final Method method;

//    private final Method bridgedMethod;

    private final MethodParameter[] parameters;

//    private final HandlerMethod resolvedFromHandlerMethod;


    private Set<String> urls;

    private Set<RequestMethod> requestMethods;


    public HandlerMethod(Object bean, Method method, Set<String> urls, Set<RequestMethod> requestMethods) {

        Assert.notNull(bean);
        Assert.notNull(method);

        this.bean = bean;
        this.beanType = bean.getClass();
        this.method = method;
        this.urls = urls;
        this.requestMethods = requestMethods;


        //init the Method parameters.
        Class<?>[] parameterTypes = this.method.getParameterTypes();
        int parameterTypesLength = parameterTypes.length;
        parameters = new MethodParameter[parameterTypesLength];

        for (int i = 0; i < parameterTypesLength; i++) {

            MethodParameter methodParameter = new MethodParameter(this.method, i);
            parameters[i] = methodParameter;

        }

    }


    @Override
    public String toString() {

        return "path=" + urls + ",methods=" + requestMethods + ",class=" + beanType.getName() + ",method=" + method.toGenericString();

    }

    public boolean matchRequestMethod(HttpServletRequest request) {
        Assert.notNull(request);

        String requestPath = request.getRequestURI();

        if (urls.contains(requestPath)) {
            if (requestMethods.isEmpty()) {
                return true;
            } else {
                if (requestMethods.contains(RequestMethod.valueOf(request.getMethod().toUpperCase()))) {
                    return true;
                }
            }
        }
        return false;
    }

    public Object[] extractArguments(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String[] values = new String[parameters.length];
        Object[] args = new Object[parameters.length];
        List<String> errors = new ArrayList<>();
        for (int i = 0; i < parameters.length; i++) {

            MethodParameter methodParameter = parameters[i];

            String parameterName = methodParameter.getParameterName();

            //if no such parameter, return null.
            String value = request.getParameter(parameterName);

            //validation.
            if (methodParameter.isRequired()) {
                if (value == null) {
                    errors.add(parameterName + " is required.");
                }
            }

            values[i] = value;
        }

        if (errors.size() != 0) {
            throw new IllegalArgumentException(errors.toString());
        }

        //cast type.
        ConversionService conversionService = AppContext.getConversionService();

        for (int i = 0; i < values.length; i++) {

            MethodParameter methodParameter = parameters[i];

            String value = values[i];

            if (value == null) {
                args[i] = null;
            } else {
                args[i] = conversionService.convert(values[i], TypeDescriptor.valueOf(String.class), TypeDescriptor.forMethodParameter(methodParameter));
            }

        }

        return args;

    }

    //handle the request.
    public void handle(HttpServletRequest request, HttpServletResponse response) {

        Object result=null;

        try {

            Object[] args = extractArguments(request, response);

            result = this.method.invoke(this.bean, args);

        } catch (Exception e) {

            logger.debug("Webzic Exception intercept.");
            logger.debug(e.getMessage());

            //handle exceptions.
            HandlerMethod handlerMethod = AppContext.getControllerAnnotationBeanFactory().getExceptionHandlerMethod();

            try {
                result = handlerMethod.getMethod().invoke(handlerMethod.bean, request, e);
            } catch (Exception e1) {
                Map<String,Object> map = new HashMap<>();
                map.put("error","Exception Controller Error!");
                result=map;
            }


        }finally {

            //check the type of result.
            String jsonResult = JsonUtil.toJson(result);

            response.setContentType("text/html");
            PrintWriter out = null;
            try {
                out = response.getWriter();
                out.println(jsonResult);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }
}
