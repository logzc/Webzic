package com.logzc.webzic.converter;

import com.logzc.webzic.converter.basic.StringToBooleanConverter;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by lishuang on 2016/8/4.
 */
public class ConversionServiceTest {

    private ConversionService conversionService;

    @Before
    public void init() {
        conversionService=new ConversionService();

        conversionService.addConverter(new StringToBooleanConverter());


    }

    @After
    public void destroy() {

    }

    @Test
    public void testStringToBoolean() {

        //String -> Boolean
        Assume.assumeTrue(conversionService.convert("true",Boolean.class));
        Assume.assumeTrue(conversionService.convert("1",Boolean.class));
        Assume.assumeTrue(conversionService.convert("on",Boolean.class));
        Assume.assumeFalse(conversionService.convert("false",Boolean.class));
        Assume.assumeFalse(conversionService.convert("0",Boolean.class));
        Assume.assumeFalse(conversionService.convert("oFf",Boolean.class));





    }

}
