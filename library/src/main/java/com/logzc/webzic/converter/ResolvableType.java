package com.logzc.webzic.converter;

import com.logzc.webzic.util.Assert;
import com.logzc.webzic.web.core.MethodParameter;

import java.lang.reflect.Type;

/**
 * encapsulate a Type.
 * Obtained from Field,Method Parameter,Method Return Type,Class
 * Created by lishuang on 2016/8/16.
 */
/*
//Target:

private HashMap<Integer, List<String>> myMap;

   public void example() {
       ResolvableType t = ResolvableType.forField(getClass().getDeclaredField("myMap"));
       t.getSuperType(); // AbstractMap<Integer, List<String>>
       t.asMap(); // Map<Integer, List<String>>
       t.getGeneric(0).resolve(); // Integer
       t.getGeneric(1).resolve(); // List
       t.getGeneric(1); // List<String>
       t.resolveGeneric(1, 0); // String
   }


 */
public class ResolvableType {


    private Type type;

    private Class<?> resolved;

    private ResolvableType superType;

    private ResolvableType[] interfaces;

    private ResolvableType[] generics;


    public ResolvableType(Class<?> sourceClass) {

        if (sourceClass == null) {
            this.resolved = Object.class;
        } else {
            this.resolved = sourceClass;
        }

    }

    public static ResolvableType forType(Type type){
        //return forType(type,null,null);

        return null;
    }

    public static ResolvableType forType(Type type,ResolvableType owner){
        return null;
    }


    public static ResolvableType forClass(Class<?> sourceClass) {
        return new ResolvableType(sourceClass);
    }


    //This is the key method. fallback is a backup.
    public Class<?> resolve(Class<?> fallback) {
        return this.resolved != null ? this.resolved : fallback;
    }

    public static ResolvableType forMethodParameter(MethodParameter methodParameter) {
        return forMethodParameter(methodParameter,null);
    }

    public static ResolvableType forMethodParameter(MethodParameter methodParameter, ResolvableType implementationsType) {

        Assert.notNull(methodParameter);

        if(implementationsType==null){
            //implementationsType=forType(methodParameter.getContainningClass());
        }

        return null;
    }


}
