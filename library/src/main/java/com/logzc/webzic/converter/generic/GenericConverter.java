package com.logzc.webzic.converter.generic;

import com.logzc.webzic.converter.ConvertiblePair;
import com.logzc.webzic.converter.TypeDescriptor;

import java.util.Set;

/**
 * Created by lishuang on 2016/8/5.
 */
public interface GenericConverter {

    Set<ConvertiblePair> getConvertibleTypes();


    Object convert(Object source, TypeDescriptor sourceType,TypeDescriptor targetType);




}
