package com.logzc.webzic.converter.generic;

import com.logzc.webzic.converter.ConvertiblePair;
import com.logzc.webzic.converter.ResolvableType;
import com.logzc.webzic.converter.TypeDescriptor;
import com.logzc.webzic.converter.basic.Converter;

import java.util.Collections;
import java.util.Set;

/**
 * Created by lishuang on 2016/9/12.
 */
@SuppressWarnings("unchecked")
public class ConverterAdapter implements GenericConverter {

    private final Converter<Object, Object> converter;

    private final ConvertiblePair pair;

    private final ResolvableType targetType;

    public ConverterAdapter(Converter<?, ?> converter, ResolvableType sourceType, ResolvableType targetType) {
        this.converter = (Converter<Object, Object>) converter;

        this.pair = new ConvertiblePair(sourceType.resolve(Object.class), targetType.resolve(Object.class));
        this.targetType = targetType;

    }

    @Override
    public Set<ConvertiblePair> getConvertiblePairs() {
        return Collections.singleton(this.pair);
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        return this.converter.convert(source);
    }
}
