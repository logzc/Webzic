package com.logzc.webzic.converter.generic;

import com.logzc.webzic.converter.ConvertiblePair;
import com.logzc.webzic.converter.ResolvableType;
import com.logzc.webzic.converter.TypeDescriptor;
import com.logzc.webzic.converter.basic.Converter;
import com.logzc.webzic.converter.basic.ConverterFactory;
import com.logzc.webzic.util.Assert;

import java.util.Collections;
import java.util.Set;

/**
 * Created by lishuang on 2016/9/12.
 */
@SuppressWarnings("unchecked")
public class ConverterFactoryAdapter implements GenericConverter {

    private final ConverterFactory<Object, Object> converterFactory;

    private final ConvertiblePair pair;


    public ConverterFactoryAdapter(ConverterFactory<?, ?> converterFactory, ConvertiblePair pair) {
        this.converterFactory = (ConverterFactory<Object, Object>) converterFactory;

        this.pair = pair;


    }

    @Override
    public Set<ConvertiblePair> getConvertiblePairs() {
        return Collections.singleton(this.pair);
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {

        Assert.notNull(source,"source cannot be null.");



        return this.converterFactory.getConverter(targetType.getObjectType()).convert(source);
    }
}
