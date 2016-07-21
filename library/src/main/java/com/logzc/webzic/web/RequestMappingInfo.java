package com.logzc.webzic.web;

import com.logzc.webzic.util.Assert;
import com.logzc.webzic.web.annotation.RequestMethod;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * This is the mapping info class.
 * Created by lishuang on 2016/7/21.
 */
@Data
public class RequestMappingInfo {

    //This is unique
    private HandlerMethod handlerMethod;

    private Set<String> urls;

    private Set<RequestMethod> requestMethods;


    public RequestMappingInfo(HandlerMethod handlerMethod, Set<String> urls, Set<RequestMethod> requestMethods) {
        this.handlerMethod = handlerMethod;
        this.urls = urls;
        this.requestMethods = requestMethods;
    }

    public boolean match(HttpServletRequest request) {
        Assert.notNull(request);

        String requestPath = request.getRequestURI();

        if (urls.contains(requestPath)) {
            if (requestMethods.contains(RequestMethod.valueOf(request.getMethod().toUpperCase()))) {
                return true;
            }
        }
        return false;
    }

}
