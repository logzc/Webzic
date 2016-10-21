package com.logzc.common.converter.generic;

import com.logzc.common.converter.ConvertiblePair;
import com.logzc.common.converter.TypeDescriptor;

import java.util.Set;

/**
 * super and child do not convert.
 * Created by lishuang on 2016/9/22.
 */
public class NoOpConverter implements GenericConverter {


    @Override
    public Set<ConvertiblePair> getConvertiblePairs() {
        return null;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        return source;
    }
}
