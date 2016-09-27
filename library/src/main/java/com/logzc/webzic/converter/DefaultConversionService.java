package com.logzc.webzic.converter;

import com.logzc.webzic.converter.basic.StringToBooleanConverter;
import com.logzc.webzic.converter.basic.StringToEnumConverterFactory;
import com.logzc.webzic.converter.basic.StringToNumberConverterFactory;
import com.logzc.webzic.converter.generic.StringToArrayConverter;
import com.logzc.webzic.converter.generic.StringToCollectionConverter;

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
