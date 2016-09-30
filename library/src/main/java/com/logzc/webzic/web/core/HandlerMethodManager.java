package com.logzc.webzic.web.core;

import com.logzc.webzic.exception.NotFoundException;
import com.logzc.webzic.web.controller.ExceptionController;
import com.logzc.webzic.web.controller.WebzicPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lishuang on 2016/9/29.
 */
public class HandlerMethodManager {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private List<HandlerMethod> handlerMethodList = new ArrayList<>();
    private Map<String, HandlerMethod> handlerMethodMap = new HashMap<>();


    private ExceptionController defaultExceptionController;


    public ExceptionController getDefaultExceptionController() {
        if (defaultExceptionController == null) {
            defaultExceptionController = new ExceptionController();
        }
        return defaultExceptionController;
    }

    public HandlerMethod getExceptionHandlerMethod() {
        return handlerMethodMap.get(WebzicPath.WEBZIC_EXCEPTION);
    }


    public HandlerMethod get(String path) {
        return handlerMethodMap.get(path);
    }


    //get the HandlerMethod by HttpServletRequest.
    public HandlerMethod get(HttpServletRequest request) {

        String path = request.getRequestURI();

        HandlerMethod handlerMethod = handlerMethodMap.get(path);

        if (handlerMethod != null && handlerMethod.matchRequestMethod(request)) {
            return handlerMethod;

        }

        throw new NotFoundException("Cannot fount " + request.getRequestURI());

    }


    public void add(String path, HandlerMethod handlerMethod) {

        logger.debug("Mapped " + handlerMethod);

        handlerMethodList.add(handlerMethod);
        handlerMethodMap.put(path, handlerMethod);
    }
}
