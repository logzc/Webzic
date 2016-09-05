package com.logzc.webzic.converter;

import com.logzc.webzic.converter.basic.*;
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
        Class<Enum1> enum1Class=Enum1.class;
        Class<?> supper=enum1Class.getSuperclass();

        StringToEnumConverterFactory factory=new StringToEnumConverterFactory();
        Converter<String,Enum1> stringEnumConverter=factory.getConverter(enum1Class);


        Enum1 apple=stringEnumConverter.convert("apple");

        System.out.println(apple);


    }

    @Test
    public void testHashCode(){
        ConvertiblePair convertiblePair1=new ConvertiblePair(String.class,Integer.class);
        ConvertiblePair convertiblePair2=new ConvertiblePair(String.class,Double.class);

        System.out.println(convertiblePair1.hashCode());
        System.out.println(convertiblePair2.hashCode());
    }
}
