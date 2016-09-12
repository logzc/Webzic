package com.logzc.webzic.converter;

import com.logzc.webzic.converter.basic.*;
import org.junit.Assume;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lishuang on 2016/8/4.
 */
public class GenericConverterTest {

    @Test
    public void testStringToBoolean() {

        String test = "true";

        System.out.println(new StringToBooleanConverter().convert(test));


    }

}
