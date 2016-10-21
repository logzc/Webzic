package com.logzc.common.converter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lishuang on 2016/8/4.
 */
public class ConverterPoolTest {


    public static enum Fruit {
        apple,
        banana,
        pear,
        orange
    }

    @Test
    public void testStringToBoolean() {


        ConverterPool pool = new ConverterPool();

        List<Class<?>> list = pool.getClassHierarchy(ArrayList.class);

        for (Class<?> clazz : list) {
            System.out.println(clazz);
        }

        List<Class<?>> list1 = pool.getClassHierarchy(Fruit.class);

        for (Class<?> clazz : list1) {
            System.out.println(clazz);
        }


    }

}
