package com.logzc.common.converter;

import com.logzc.common.converter.basic.StringToBooleanConverter;
import com.logzc.common.converter.basic.StringToEnumConverterFactory;
import com.logzc.common.converter.basic.StringToNumberConverterFactory;
import com.logzc.common.converter.generic.StringToArrayConverter;
import com.logzc.common.converter.generic.StringToCollectionConverter;

/**
 * Created by lishuang on 2016/9/22.
 */
public class DefaultConversionService extends ConversionService {


    public DefaultConversionService() {

        //scalar converts.
        addConverter(new StringToBooleanConverter());

        //basic
        addConverter(new StringToBooleanConverter());
        addConverterFactory(new StringToNumberConverterFactory());
        addConverterFactory(new StringToEnumConverterFactory());

        //generic
        addConverter(new StringToArrayConverter(this));
        addConverter(new StringToCollectionConverter(this));
    }

}
