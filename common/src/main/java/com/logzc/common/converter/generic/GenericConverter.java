package com.logzc.common.converter.generic;

import com.logzc.common.converter.ConvertiblePair;
import com.logzc.common.converter.TypeDescriptor;

import java.util.Set;

/**
 * Created by lishuang on 2016/8/5.
 */
public interface GenericConverter {

    Set<ConvertiblePair> getConvertiblePairs();


    Object convert(Object source, TypeDescriptor sourceType,TypeDescriptor targetType);




}
