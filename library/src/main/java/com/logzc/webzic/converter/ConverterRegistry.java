package com.logzc.webzic.converter;

import com.logzc.webzic.converter.basic.Converter;
import com.logzc.webzic.converter.basic.ConverterFactory;

/**
 * Created by lishuang on 2016/8/4.
 */
public interface ConverterRegistry {

    void addConverter(Converter<?, ?> converter);

    void addConverter(Class<?> sourceType, Class<?> targetType, Converter<?, ?> converter);

    void addConverterFactory(ConverterFactory<?, ?> converterFactory);

    Converter<?,?> getConverter(Class<?> sourceType,Class<?> targetType);
}
