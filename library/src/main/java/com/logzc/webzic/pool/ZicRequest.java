package com.logzc.webzic.pool;

import com.logzc.webzic.annotation.RequestMethod;
import com.logzc.webzic.util.builder.EqualsBuilders;
import com.logzc.webzic.util.builder.HashCodeBuilders;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Container the request's path and method.
 * Created by lishuang on 2016/7/19.
 */
public class ZicRequest {
    private String path;
    private Set<RequestMethod> methods;

    public ZicRequest(String path, RequestMethod... requestMethods) {
        this.path = path;
        methods = new HashSet<>();
        if (requestMethods != null && requestMethods.length > 0) {
            Collections.addAll(methods, requestMethods);
        }
    }

    public String getPath() {
        return path;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilders.reflectionHashCode(this, "methods");
    }

    @Override
    public boolean equals(Object object) {
        return EqualsBuilders.reflectionEquals(this, object, "methods");
    }
}
