package com.logzc.webzic.converter.generic;

import com.logzc.webzic.converter.CollectionFactory;
import com.logzc.webzic.converter.ConversionService;
import com.logzc.webzic.converter.ConvertiblePair;
import com.logzc.webzic.converter.TypeDescriptor;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * Created by lishuang on 2016/9/5.
 */
public class StringToCollectionConverter implements GenericConverter {

    private final ConversionService conversionService;

    public StringToCollectionConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

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

        String[] fields = str.split(",");


        //String[] element->String.
        TypeDescriptor elementDescriptor = targetType.getElementTypeDescriptor();

        Class<?> elementType = elementDescriptor == null ? null : elementDescriptor.getType();
        Collection<Object> target = CollectionFactory.createCollection(targetType.getType(), elementType, fields.length);


        if (elementDescriptor == null) {
            for (String field : fields) {
                target.add(field.trim());
            }
        } else {
            for (String field : fields) {


                Object targetElement=this.conversionService.convert(field.trim(),sourceType,elementDescriptor);

                target.add(targetElement);

            }
        }


        return target;
    }


}
