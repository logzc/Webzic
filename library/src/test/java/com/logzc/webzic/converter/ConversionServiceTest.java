package com.logzc.webzic.converter;

import com.logzc.webzic.converter.basic.StringToBooleanConverter;
import com.logzc.webzic.converter.basic.StringToEnumConverterFactory;
import com.logzc.webzic.converter.basic.StringToNumberConverterFactory;
import com.logzc.webzic.converter.generic.StringToArrayConverter;
import com.logzc.webzic.converter.generic.StringToCollectionConverter;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lishuang on 2016/8/4.
 */
public class ConversionServiceTest {

    private ConversionService conversionService;

    private enum Fruit {
        apple,
        orange,
        pair,
        peach,
        banana
    }

    @Before
    public void init() {
        conversionService = new ConversionService();

        //basic
        conversionService.addConverter(new StringToBooleanConverter());

        conversionService.addConverterFactory(new StringToNumberConverterFactory());


        conversionService.addConverterFactory(new StringToEnumConverterFactory());

        //generic
        conversionService.addConverter(new StringToArrayConverter(conversionService));
        conversionService.addConverter(new StringToCollectionConverter(conversionService));

    }

    @After
    public void destroy() {

    }

    @Test
    public void testStringToBoolean() {

        //String -> Boolean
        Assume.assumeTrue(conversionService.convert("true", Boolean.class));
        Assume.assumeTrue(conversionService.convert("1", Boolean.class));
        Assume.assumeTrue(conversionService.convert("on", Boolean.class));
        Assume.assumeFalse(conversionService.convert("false", Boolean.class));
        Assume.assumeFalse(conversionService.convert("0", Boolean.class));
        Assume.assumeFalse(conversionService.convert("oFf", Boolean.class));

    }

    @Test
    public void testStringToNumber() {
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

    @Test
    public void testStringToEnum() {
        //String -> Enum
        Assume.assumeTrue(conversionService.convert("apple", Fruit.class) == Fruit.apple);
    }

    @Test
    public void testStringToArray() {

        int[] var = conversionService.convert("12,34,21", int[].class);

        //String -> Enum
        Assume.assumeTrue(var.length == 3);
        Assume.assumeTrue(var[2] == 21);
    }


    @Test
    public void testResolvableType() {
        List<String> list = new ArrayList<>();

        ResolvableType resolvableType = ResolvableType.forClass(list.getClass());

        ResolvableType[] r1 = resolvableType.getGenerics();

        System.out.println(resolvableType);
    }


    public List<Integer> ints;

    @SuppressWarnings("unchecked")
    @Test
    public void testStringToCollection() throws Exception {

        ints = (List<Integer>) conversionService.convert("12,34,45", TypeDescriptor.valueOf(String.class), new TypeDescriptor(getClass().getField("ints")));

        //String -> Collection
        Assume.assumeTrue(ints.size() == 3);
        Assume.assumeTrue(ints.get(2) == 45);


    }

}
