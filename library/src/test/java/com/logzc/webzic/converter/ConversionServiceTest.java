package com.logzc.webzic.converter;

import com.logzc.webzic.converter.basic.StringToBooleanConverter;
import com.logzc.webzic.converter.basic.StringToNumberConverterFactory;
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

        conversionService.addConverterFactory(new StringToNumberConverterFactory());

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


        //String -> Number
        Assume.assumeTrue(conversionService.convert("11.0", Double.class) == 11.0);
        Assume.assumeTrue(conversionService.convert("11.0f", Float.class) == 11.0f);
        Assume.assumeTrue(conversionService.convert("11", Integer.class) == 11);
        Assume.assumeTrue(conversionService.convert("11", Long.class) == 11L);
        Assume.assumeTrue(conversionService.convert("11.0", double.class) == 11.0);
        Assume.assumeTrue(conversionService.convert("11.0f", float.class) == 11.0f);
        Assume.assumeTrue(conversionService.convert("11", int.class) == 11);
        Assume.assumeTrue(conversionService.convert("11", long.class) == 11L);



    }

}
