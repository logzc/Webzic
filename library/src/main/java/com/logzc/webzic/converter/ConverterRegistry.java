package com.logzc.webzic.converter;

import com.logzc.webzic.converter.basic.Converter;
import com.logzc.webzic.converter.basic.ConverterFactory;
import com.logzc.webzic.converter.generic.GenericConverter;

/**
 * Created by lishuang on 2016/8/4.
 */
public interface ConverterRegistry {

    void addConverter(Converter<?, ?> converter);


    void addConverter(GenericConverter converter);

    void addConverterFactory(ConverterFactory<?, ?> converterFactory);

    Converter<?, ?> getConverter(Class<?> sourceType, Class<?> targetType);


    GenericConverter getConverter(TypeDescriptor sourceType, TypeDescriptor targetType);
}
