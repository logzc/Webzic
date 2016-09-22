package com.logzc.webzic.converter;

import com.logzc.webzic.converter.basic.Converter;
import com.logzc.webzic.converter.basic.ConverterFactory;
import com.logzc.webzic.converter.generic.ConverterAdapter;
import com.logzc.webzic.converter.generic.GenericConverter;
import com.logzc.webzic.converter.generic.NoOpConverter;
import com.logzc.webzic.util.Assert;

/**
 * Created by lishuang on 2016/8/19.
 */
public class ConversionService implements ConverterRegistry, Conversion {

    private final ConverterPool converterPool = new ConverterPool();

    private static final GenericConverter NO_OP_CONVERTER = new NoOpConverter();

    @Override
    public void addConverter(Converter<?, ?> converter) {

        ResolvableType resolvableType = ResolvableType.forClass(converter.getClass()).as(Converter.class);
        ResolvableType[] generics = resolvableType.getGenerics();
        if (generics.length < 2) {
            throw new IllegalArgumentException("converter doesn't have two generics.");
        }
        Class<?> sourceType = generics[0].resolve();
        Class<?> targetType = generics[1].resolve();
        if (sourceType == null || targetType == null) {
            throw new IllegalArgumentException("converter doesn't have two generics.");
        }

        GenericConverter genericConverter = new ConverterAdapter(converter, generics[0], generics[1]);

        addConverter(genericConverter);

    }

    @Override
    public void addConverter(GenericConverter converter) {

        this.converterPool.add(converter);


    }

    @Override
    public void addConverterFactory(ConverterFactory<?, ?> converterFactory) {

    }


    public GenericConverter getDefaultConverter(TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (sourceType.getType().isAssignableFrom(targetType.getType())) {
            return NO_OP_CONVERTER;
        } else {
            return null;
        }
    }

    @Override
    public Converter<?, ?> getConverter(Class<?> sourceType, Class<?> targetType) {


        return null;
    }

    @Override
    public GenericConverter getConverter(TypeDescriptor sourceType, TypeDescriptor targetType) {
        GenericConverter converter = this.converterPool.getConverter(sourceType, targetType);

        if (converter == null) {

        }

        return converter;
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
