package com.logzc.webzic.converter;

import com.logzc.webzic.util.ReflectionUtil;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by lishuang on 2016/9/6.
 */
public class ResolveTypeTest<T> {



    private HashMap<Integer, List<String>> myMap;
    private T[] list;

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
        ResolvableType asMapType = t.asMap();

        // Integer
        Type generic0Type = t.getGeneric(0).resolve();

        // List
        Type generic1Type = t.getGeneric(1).resolve();

        // List<String>
        ResolvableType generic1 = t.getGeneric(1);

        // String
        Type generic10 = t.getGeneric(1, 0).resolve();



        ResolveTypeTest<String> test=new ResolveTypeTest<>();
        ResolvableType t1 = ResolvableType.forField(test.getClass().getDeclaredField("list"));

        Type type1=t1.resolve();

        System.out.println("Finish.");

    }


    @Test
    public void testFindMethod(){


        Class<?> clazz=List.class;

        Method[] methods1 = clazz.getDeclaredMethods();

        Set<String> set=new HashSet<>();
        for (Method method:methods1){
            set.add(method.toGenericString());
        }
        Method[] methods2 = clazz.getMethods();
        for (Method method:methods2){
            String str=method.toGenericString();
            if(!set.contains(str)){
                System.out.println(str);
            }
        }

        clazz=ArrayList.class;

        methods1 = clazz.getDeclaredMethods();

        set=new HashSet<>();
        for (Method method:methods1){
            set.add(method.toGenericString());
        }
        methods2 = clazz.getMethods();
        for (Method method:methods2){
            String str=method.toGenericString();
            if(!set.contains(str)){
                System.out.println(str);
            }
        }


        Method  method = ReflectionUtil.findMethod(clazz,"size");

        System.out.println(method);

    }

}
