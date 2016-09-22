package com.logzc.webzic.converter;

import com.logzc.webzic.converter.basic.StringToBooleanConverter;

/**
 * Created by lishuang on 2016/9/22.
 */
public class DefaultConversionService extends ConversionService {



    public DefaultConversionService(){

        //scalar converts.
        addConverter(new StringToBooleanConverter());


    }

}
