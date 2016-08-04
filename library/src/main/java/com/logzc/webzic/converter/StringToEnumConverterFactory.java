package com.logzc.webzic.converter;

/**
 * Created by lishuang on 2016/8/4.
 */
public class StringToEnumConverterFactory implements ConverterFactory<String, Enum> {
    @Override
    public <T extends Enum> Converter<String, T> getConverter(Class<T> targetClass) {

        Class<?> enumType = targetClass;
        
        return null;
    }
}
