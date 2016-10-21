package com.logzc.common.converter;

/**
 * Created by lishuang on 2016/8/19.
 */
public interface Conversion {

    boolean canConvert(Class<?> sourceType, Class<?> targetType);


    <T> T convert(Object source, Class<T> targetType);

    Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType);
}
