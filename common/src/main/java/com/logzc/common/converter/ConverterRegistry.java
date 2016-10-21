package com.logzc.common.converter;

import com.logzc.common.converter.basic.Converter;
import com.logzc.common.converter.basic.ConverterFactory;
import com.logzc.common.converter.generic.GenericConverter;

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
