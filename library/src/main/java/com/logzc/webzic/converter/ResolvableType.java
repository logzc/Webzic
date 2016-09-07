package com.logzc.webzic.converter;

import com.logzc.webzic.converter.provider.TypeProvider;
import com.logzc.webzic.converter.resolver.VariableResolver;
import com.logzc.webzic.util.Assert;
import com.logzc.webzic.util.ObjectUtil;
import com.logzc.webzic.web.core.MethodParameter;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collection;

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

    public static final ResolvableType NONE = new ResolvableType(null);

    private Type type;

    private Class<?> resolved;

    private ResolvableType superType;
    private TypeProvider typeProvider;
    private VariableResolver variableResolver;

    private ResolvableType[] interfaces;

    private ResolvableType[] generics;


    public ResolvableType(Type type, TypeProvider typeProvider, VariableResolver variableResolver){

        this.type=type;
        this.typeProvider=typeProvider;
        this.variableResolver=variableResolver;
    }

    public ResolvableType(Class<?> sourceClass) {

        if (sourceClass == null) {
            this.resolved = Object.class;
        } else {
            this.resolved = sourceClass;
        }
    }

    public static ResolvableType forType(Type type) {
        //return forType(type,null,null);

        return null;
    }


    public static ResolvableType forType(Type type, TypeProvider typeProvider, VariableResolver variableResolver) {

        if (type == null && typeProvider != null) {
            type = SerializableTypeWrapper.forTypeProvider(typeProvider);
        }

        if (type == null) {
            return NONE;
        }

        if(type instanceof Class){
            return new ResolvableType(type,typeProvider,variableResolver);
        }


        //TODO::STUCK HERE.
        return null;

    }

    public static ResolvableType forField(Field field) {
        Assert.notNull(field, "Field must not be null.");

        //STOP AT HERE.
        //return forType(null,new FieldTypeProvider(field),null);
        return null;
    }

    public static ResolvableType forType(Type type, ResolvableType owner) {
        return null;
    }


    public static ResolvableType forClass(Class<?> sourceClass) {
        return new ResolvableType(sourceClass);
    }

    public Class<?> resolve() {
        return resolve(null);
    }

    //This is the key method. fallback is a backup.
    public Class<?> resolve(Class<?> fallback) {
        return this.resolved != null ? this.resolved : fallback;
    }

    public static ResolvableType forMethodParameter(MethodParameter methodParameter) {
        return forMethodParameter(methodParameter, null);
    }

    public static ResolvableType forMethodParameter(MethodParameter methodParameter, ResolvableType implementationsType) {

        Assert.notNull(methodParameter);

        if (implementationsType == null) {
            //implementationsType=forType(methodParameter.getContainningClass());
        }

        return null;
    }


    public ResolvableType[] getInterfaces() {

        Class<?> resolved = resolve();

        if (resolved == null || ObjectUtil.isEmpty(resolved.getGenericInterfaces())) {
            return new ResolvableType[0];
        }

        //lazy load.
        if (this.interfaces == null) {


            //TODO:get interfaces by this type.
            //this.interfaces=forType();

        }
        return this.interfaces;
    }

    public ResolvableType getSuperType() {

        //TODO: get the super Class Type.

        return null;
    }


    public ResolvableType asCollection() {
        return as(Collection.class);
    }


    //Convert the type.
    public ResolvableType as(Class<?> type) {
        if (this == NONE) {
            return NONE;
        }

        //No need to convert.
        if (ObjectUtil.equals(resolve(), type)) {
            return this;
        }

        ResolvableType[] thisInterfaces = getInterfaces();
        for (ResolvableType interfaceType : thisInterfaces) {


            //Recursive here.
            ResolvableType interfaceAsType = interfaceType.as(type);

            if (interfaceAsType != NONE) {
                return interfaceAsType;
            }

        }

        return getSuperType().as(type);


    }
}
