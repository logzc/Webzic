package com.logzc.webzic.converter;

import com.logzc.webzic.converter.basic.Converter;
import com.logzc.webzic.converter.basic.ConverterFactory;

/**
 * Created by lishuang on 2016/8/19.
 */
public class ConversionService implements ConverterRegistry,Conversion {
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

    @Override
    public boolean canConvert(Class<?> sourceType, Class<?> targetType) {
        return false;
    }

    @Override
    public <T> T convert(Object source, Class<T> targetType) {
        return null;
    }
}
