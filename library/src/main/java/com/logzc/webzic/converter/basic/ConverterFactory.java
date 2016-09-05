package com.logzc.webzic.converter.basic;

/**
 * Created by lishuang on 2016/8/4.
 */
public interface ConverterFactory<S, R> {

    <T extends R> Converter<S, T> getConverter(Class<T> targetClass);
}
