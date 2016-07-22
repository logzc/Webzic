package com.logzc.webzic.web;

import com.logzc.webzic.util.Assert;
import com.logzc.webzic.web.annotation.RequestMethod;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Set;

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

    }

    public boolean match(HttpServletRequest request) {
        Assert.notNull(request);

        String requestPath = request.getRequestURI();

        if (urls.contains(requestPath)) {
            if(requestMethods.isEmpty()){
                return true;
            }else{
                if (requestMethods.contains(RequestMethod.valueOf(request.getMethod().toUpperCase()))) {
                    return true;
                }
            }
        }
        return false;
    }
}
