package com.logzc.common.util.builder;

import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * wrap the apache's common method into my class.
 * Created by lishuang on 2016/7/19.
 */
public class EqualsBuilders {
    public static boolean reflectionEquals(final Object lhs, final Object rhs, final String... excludeFields) {
        return EqualsBuilder.reflectionEquals(lhs, rhs, excludeFields);
    }
}
