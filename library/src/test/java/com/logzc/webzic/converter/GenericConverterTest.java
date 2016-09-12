package com.logzc.webzic.converter;

import com.logzc.webzic.converter.basic.StringToBooleanConverter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by lishuang on 2016/8/4.
 */
public class GenericConverterTest {

    private ConversionService conversionService;

    @Before
    public void init() {
        conversionService=new ConversionService();

        
    }

    @After
    public void destroy() {

    }

    @Test
    public void testStringToBoolean() {

        String test = "true";

        System.out.println(new StringToBooleanConverter().convert(test));


    }

}
