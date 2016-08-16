package com.logzc.webzic.converter;

import java.util.Set;

/**
 * Created by lishuang on 2016/8/4.
 */
public class DefaultConverterRegistry implements ConverterRegistry {

    @Override
    public void addConverter(Converter<?, ?> converter) {

    }

    @Override
    public void addConverter(Class<?> sourceType, Class<?> targetType, Converter<?, ?> converter) {

    }

    @Override
    public void addConverterFactory(ConverterFactory<?, ?> converterFactory) {

    }

    @Override
    public Converter<?, ?> getConverter(Class<?> sourceType, Class<?> targetType) {
        return null;
    }


}
