package com.logzc.webzic.converter;

import org.junit.Test;

/**
 * Created by lishuang on 2016/8/4.
 */
public class ConverterTest {

    @Test
    public void testStringToBoolean() {

        String test = "true";

        System.out.println(new StringToBooleanConverter().convert(test));


    }

    @Test
    public void testStringToCharacter() {

        String test = "a";

        System.out.println(new StringToCharacterConverter().convert(test));


    }


    @Test
    public void testNumberConverterFactory() {

        String a = "1 2 . 34f";
        StringToNumberConverterFactory factory = new StringToNumberConverterFactory();
        Converter<String,Float> converter = factory.getConverter(Float.class);

        System.out.println(converter.convert(a));
    }


    @Test
    public void testEnum(){
        Class<?> enum1Class=Enum1.class;


        while (enum1Class!=null&&!enum1Class.isEnum()){

            System.out.println(enum1Class);
            enum1Class=enum1Class.getSuperclass();
        }


    }
}
