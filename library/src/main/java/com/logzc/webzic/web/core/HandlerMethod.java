package com.logzc.webzic.web.core;

import com.logzc.webzic.util.Assert;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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


        parameters = null;


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


    //handle the request.
    public void handle(HttpServletRequest request, HttpServletResponse response) {


        try {
            Object result = this.method.invoke(this.bean, "method not found");


        } catch (Exception e) {
            logger.debug(e.getMessage());
        }

//        try {
//            response.setContentType("text/html");
//
//            logger.debug(request.getMethod());
//
//
//            PrintWriter out = response.getWriter();
//            out.println("<h1>Hello Handler Method.</h1>");
//        } catch (Exception e) {
//            logger.debug("Exceptions:" + e.getMessage());
//        }


    }
}
