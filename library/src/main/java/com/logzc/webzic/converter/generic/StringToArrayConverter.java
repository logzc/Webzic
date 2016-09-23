package com.logzc.webzic.converter.generic;

import com.logzc.webzic.converter.ConversionService;
import com.logzc.webzic.converter.ConvertiblePair;
import com.logzc.webzic.converter.TypeDescriptor;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Set;

/**
 * Created by lishuang on 2016/9/5.
 */
public class StringToArrayConverter implements GenericConverter {

    private final ConversionService conversionService;

    public StringToArrayConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Set<ConvertiblePair> getConvertiblePairs() {
        return Collections.singleton(new ConvertiblePair(String.class, Object[].class));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {

        if (source == null) {
            return null;
        }

        String str = (String) source;

        String[] fields = str.split(",");


        Object target = Array.newInstance(targetType.getElementTypeDescriptor().getType(), fields.length);

        for (int i = 0; i < fields.length; i++) {
            String sourceElement = fields[i];
            Object targetElement = this.conversionService.convert(sourceElement.trim(), sourceType, targetType.getElementTypeDescriptor());
            Array.set(target,i,targetElement);
        }

        return target;
    }


}
