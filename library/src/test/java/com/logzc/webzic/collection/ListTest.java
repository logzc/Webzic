package com.logzc.webzic.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lishuang on 2016/8/3.
 */
public class ListTest {


    @Test
    public void testNull(){

        List<String> stringList=new ArrayList<>();

        stringList.add("lishuang");

        stringList.add("age");
        stringList.add(null);
        stringList.add(null);
        stringList.add("address");

        System.out.println(stringList);

        String a=stringList.get(2);

        System.out.println(a);
    }
}
