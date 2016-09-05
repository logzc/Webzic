package com.logzc.webzic.converter.generic;

import com.logzc.webzic.converter.ConvertiblePair;
import com.logzc.webzic.converter.TypeDescriptor;
import com.logzc.webzic.util.StringUtil;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * Created by lishuang on 2016/9/5.
 */
public class StringToCollectionConverter implements GenericConverter {
    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(String.class, Collection.class));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {

        if (source == null) {
            return null;
        }

        String str = (String) source;

        String[] fields= str.split(",");


        TypeDescriptor elementDescriptor=targetType.getElementTypeDescriptor();

        //TODO: KEEP Going.



        return null;
    }
}
