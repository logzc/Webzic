package com.logzc.webzic.converter;

/**
 * Created by lishuang on 2016/8/4.
 */
public interface ConverterRegistry {

    void addConverter(Converter<?, ?> converter);

    void addConverter(Class<?> sourceType, Class<?> targetType, Converter<?, ?> converter);

    void addConverterFactory(ConverterFactory<?, ?> converterFactory);
}
