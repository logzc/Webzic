package com.logzc.webzic.generic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lishuang on 2016/8/4.
 */
public class GenericTest {

    @Test
    public void testGenericClass(){
        Apple<String> a1=new Apple<>("苹果");
        Apple<Double> a2=new Apple<>(5.67);

        System.out.println(a1.getInfo());
        System.out.println(a2.getInfo());

    }

    @Test
    public void testClassComp(){

        List<String> list1=new ArrayList<>();
        List<Integer> list2=new ArrayList<>();

        Class<?> class1=list1.getClass();
        Class<?> class2=list2.getClass();
        System.out.println(class1==class2);

    }
}
