package com.logzc.webzic.converter;

import com.logzc.webzic.converter.provider.FieldTypeProvider;
import com.logzc.webzic.converter.provider.TypeProvider;
import com.logzc.webzic.converter.resolver.DefaultVariableResolver;
import com.logzc.webzic.converter.resolver.VariableResolver;
import com.logzc.webzic.util.Assert;
import com.logzc.webzic.util.ObjectUtil;
import com.logzc.webzic.web.core.MethodParameter;

import java.lang.reflect.*;
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


    public ResolvableType(Type type, TypeProvider typeProvider, VariableResolver variableResolver) {

        this.type = type;
        this.typeProvider = typeProvider;
        this.variableResolver = variableResolver;

        this.resolved = resolveClass();
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

        return new ResolvableType(type, typeProvider, variableResolver);

    }

    public static ResolvableType forField(Field field) {
        Assert.notNull(field, "Field must not be null.");

        return forType(null, new FieldTypeProvider(field), null);
    }

    public static ResolvableType forType(Type type, ResolvableType owner) {
        return null;
    }


    public static ResolvableType forClass(Class<?> sourceClass) {
        return new ResolvableType(sourceClass);
    }


    private Class<?> resolveClass() {

        if (this.type instanceof Class || this.type == null) {
            return (Class<?>) this.type;
        }

        //TODO:Don't know how to do this yet.
        if (this.type instanceof GenericArrayType) {
            return null;
        }

        ResolvableType tempType = resolvedType();

        return tempType.resolve();
    }

    private Type resolveBounds(Type[] bounds) {

        if (ObjectUtil.isEmpty(bounds) || Object.class == bounds[0]) {
            return null;
        }
        return bounds[0];
    }


    //erase the generic. List<String> -> List
    private ResolvableType resolvedType() {

        //eg.this.type -> Base<Integer,String>
        if (this.type instanceof ParameterizedType) {

            //eg. tempType -> Base
            Type tempType = ((ParameterizedType) this.type).getRawType();
            return forType(tempType, null, this.variableResolver);

        }

        //eg. this.type -> Base<? extend Table>
        if (this.type instanceof WildcardType) {

            // ? extends T  (T is Upper Bounds, default Object.)
            Type resolvedBoundType = resolveBounds(((WildcardType) this.type).getUpperBounds());
            if (resolvedBoundType == null) {

                resolvedBoundType = resolveBounds(((WildcardType) this.type).getLowerBounds());
            }

            return forType(resolvedBoundType, null, this.variableResolver);

        }

        if (this.type instanceof TypeVariable) {
            TypeVariable<?> typeVariable = (TypeVariable<?>) this.type;

            if (this.variableResolver != null) {
                ResolvableType resolved = this.variableResolver.resolveVariable(typeVariable);
                if (resolved != null) {
                    return resolved;
                }
            }

            //Fallback to Bounds.
            return forType(resolveBounds(typeVariable.getBounds()), null, this.variableResolver);

        }

        return NONE;

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

        Class<?> resolved = resolve();

        if (resolved == null || resolved.getGenericSuperclass() == null) {
            return NONE;
        }

        if (this.superType == null) {
            this.superType = forType(SerializableTypeWrapper.forGenericSuperclass(resolved), null, asVariableResolver());
        }


        return this.superType;
    }

    public VariableResolver asVariableResolver() {
        if (this == NONE) {
            return null;
        }

        return new DefaultVariableResolver();
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
