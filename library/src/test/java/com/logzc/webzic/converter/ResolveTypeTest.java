package com.logzc.webzic.converter;

import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lishuang on 2016/9/6.
 */
public class ResolveTypeTest {



    private HashMap<Integer, List<String>> myMap;

    @Test
    public void test1(){

        List<String> a = new ArrayList<>();

        Type type = a.getClass();

        ResolvableType resolvableType = ResolvableType.forType(type);

        ResolvableType[] interfaces = resolvableType.getInterfaces();

        System.out.println(resolvableType);

        System.out.println(interfaces);


    }

    @Test
    public void testMap()  throws Exception {

        ResolvableType t = ResolvableType.forField(getClass().getDeclaredField("myMap"));

        //AbstractMap<K,V>
        ResolvableType superType = t.getSuperType();
        // Map<Integer,List<String>>
//        ResolvableType asMapType = t.asMap();
//        Type generic0Type = t.getGeneric(0).resolve(); // Integer
//        Type generic1Type = t.getGeneric(1).resolve(); // List
//        ResolvableType generic1 = t.getGeneric(1); // List<String>
//        Type generic10 = t.resolveGeneric(1, 0); // String


        System.out.println("Finish.");

    }

}
