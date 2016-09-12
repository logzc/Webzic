package com.logzc.webzic.converter;

import com.logzc.webzic.converter.basic.Converter;
import com.logzc.webzic.converter.basic.ConverterFactory;
import com.logzc.webzic.converter.generic.GenericConverter;
import com.logzc.webzic.util.Assert;

/**
 * Created by lishuang on 2016/8/19.
 */
public class ConversionService implements ConverterRegistry, Conversion {
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
    public GenericConverter getConverter(TypeDescriptor sourceType, TypeDescriptor targetType) {

        return null;
    }

    @Override
    public boolean canConvert(Class<?> sourceType, Class<?> targetType) {
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T convert(Object source, Class<T> targetType) {


        Assert.notNull(targetType, "Target Type to convert cannot be null.");

        Object obj = convert(source, TypeDescriptor.forObject(source), TypeDescriptor.valueOf(targetType));


        return (T) obj;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {

        Assert.notNull(targetType, "Target Type cannot be null.");


        GenericConverter converter = getConverter(sourceType, targetType);

        Object obj = null;
        if (converter != null) {
            obj = converter.convert(source, sourceType, targetType);
        } else {
            throw new IllegalArgumentException("Cannot convert from " + sourceType.getType().getName() + " to " + targetType.getType().getName());
        }

        return obj;
    }
}
