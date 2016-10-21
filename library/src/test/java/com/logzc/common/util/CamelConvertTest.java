package com.logzc.common.util;

import org.junit.Test;

/**
 * Created by lishuang on 2016/8/26.
 */
public class CamelConvertTest {

    @Test
    public void testConvert(){
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        System.out.println("camelCaseToSomethingElse"
                .replaceAll(regex, replacement)
                .toLowerCase());


    }
}
