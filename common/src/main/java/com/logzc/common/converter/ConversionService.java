package com.logzc.common.converter;

import com.logzc.common.converter.basic.Converter;
import com.logzc.common.converter.basic.ConverterFactory;
import com.logzc.common.converter.generic.ConverterAdapter;
import com.logzc.common.converter.generic.ConverterFactoryAdapter;
import com.logzc.common.converter.generic.GenericConverter;
import com.logzc.common.converter.generic.NoOpConverter;
import com.logzc.common.util.Assert;

/**
 * Created by lishuang on 2016/8/19.
 */
public class ConversionService implements ConverterRegistry, Conversion {

    private final ConverterPool converterPool = new ConverterPool();

    private static final GenericConverter NO_OP_CONVERTER = new NoOpConverter();

    @Override
    public void addConverter(Converter<?, ?> converter) {

        ResolvableType[] generics = getRequiredTypeInfo(converter, Converter.class);

        GenericConverter genericConverter = new ConverterAdapter(converter, generics[0], generics[1]);

        addConverter(genericConverter);

    }

    @Override
    public void addConverterFactory(ConverterFactory<?, ?> converterFactory) {
        ResolvableType[] generics = getRequiredTypeInfo(converterFactory, ConverterFactory.class);
        ConvertiblePair pair = new ConvertiblePair(generics[0].resolve(), generics[1].resolve());
        GenericConverter genericConverter = new ConverterFactoryAdapter(converterFactory, pair);

        addConverter(genericConverter);
    }

    //For
    //addConverter(Converter<?, ?> converter) and addConverterFactory(ConverterFactory<?, ?> converterFactory)
    private ResolvableType[] getRequiredTypeInfo(Object converter, Class<?> genericClass) {
        ResolvableType resolvableType = ResolvableType.forClass(converter.getClass()).as(genericClass);
        ResolvableType[] generics = resolvableType.getGenerics();
        if (generics.length < 2) {
            throw new IllegalArgumentException("converter doesn't have two generics.");
        }
        Class<?> sourceType = generics[0].resolve();
        Class<?> targetType = generics[1].resolve();
        if (sourceType == null || targetType == null) {
            throw new IllegalArgumentException("converter doesn't have two generics.");
        }
        return generics;
    }

    @Override
    public void addConverter(GenericConverter converter) {

        this.converterPool.add(converter);


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

        if (source != null && !sourceType.getObjectType().isInstance(source)) {
            throw new IllegalArgumentException("source to convert from must be an instance of " +
                    sourceType + "; instead it was a " + source.getClass().getName());
        }

        GenericConverter converter = getConverter(sourceType, targetType);

        Object obj = null;
        if (converter != null) {
            obj = converter.convert(source, sourceType, targetType);
        } else {

            //sourceType extends targetType
            if (sourceType.isAssignableTo(targetType)) {
                obj = NO_OP_CONVERTER.convert(source, sourceType, targetType);
            } else {
                throw new IllegalArgumentException("Cannot convert from " + sourceType.getType().getName() + " to " + targetType.getType().getName());
            }
        }

        return obj;
    }
}
