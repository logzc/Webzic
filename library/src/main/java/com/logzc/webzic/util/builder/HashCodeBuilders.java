package com.logzc.webzic.util.builder;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Collection;

/**
 * an adapter for apache's common.
 * Created by lishuang on 2016/7/19.
 */
public class HashCodeBuilders {

    public static int reflectionHashCode(final Object object, final Collection<String> excludeFields) {
        return HashCodeBuilder.reflectionHashCode(object, excludeFields);
    }

    public static int reflectionHashCode(final Object object, final String... excludeFields) {
        return HashCodeBuilder.reflectionHashCode(object, excludeFields);
    }
}
