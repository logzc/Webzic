package com.logzc.webzic.converter;

import com.logzc.webzic.converter.provider.*;
import com.logzc.webzic.util.Assert;
import com.logzc.webzic.web.core.MethodParameter;

import java.io.Serializable;
import java.lang.reflect.*;

/**
 * Created by lishuang on 2016/8/22.
 */
abstract public class TypeWrapper {


    private static final Class<?>[] SUPPORTED_SERIALIZABLE_TYPES = {
            GenericArrayType.class,
            ParameterizedType.class,
            TypeVariable.class,
            WildcardType.class
    };


    //Get a Type from a TypeProvider.
    public static Type forTypeProvider(TypeProvider typeProvider) {
        Assert.notNull(typeProvider, "Provider must not null.");

        //"type instanceof Serializable" to check whether type is a Generic.
        Type type0 = typeProvider.getType();

        if(System.currentTimeMillis()>0){
            return type0;
        }

        if (type0 instanceof Serializable || type0 == null) {
            return type0;
        }

        //ready to construct an implement of special Type.

        for (Class<?> type : SUPPORTED_SERIALIZABLE_TYPES) {

            //typeA.isAssignableFrom(typeB) means typeA is super class of typeB
            if (type.isAssignableFrom(type0.getClass())) {
                ClassLoader classLoader = typeProvider.getClass().getClassLoader();
                Class<?>[] interfaces = new Class<?>[]{
                        type,
                        SerializableTypeProxy.class,
                        Serializable.class
                };
                InvocationHandler handler = new TypeProxyInvocationHandler(typeProvider);
                Object newTypeInstance = Proxy.newProxyInstance(classLoader, interfaces, handler);
                return (Type) newTypeInstance;
            }
        }
        throw new IllegalArgumentException("Unsupported Type class: " + typeProvider.getType().getClass().getName());
    }

    public static Type forField(Field field) {
        Assert.notNull(field, "Field must not be null.");
        return forTypeProvider(new FieldTypeProvider(field));
    }

    public static Type forMethodParameter(MethodParameter methodParameter) {
        return forTypeProvider(new MethodParameterTypeProvider(methodParameter));
    }

    public static Type forGenericSuperclass(final Class<?> type) {
        return forTypeProvider(new DefaultTypeProvider() {
            @Override
            public Type getType() {
                return type.getGenericSuperclass();
            }
        });
    }

    public static Type[] forGenericInterfaces(final Class<?> type) {
        Type[] result = new Type[type.getGenericInterfaces().length];
        for (int i = 0; i < result.length; i++) {
            final int index = i;
            result[i] = forTypeProvider(new DefaultTypeProvider() {
                @Override
                public Type getType() {
                    return type.getGenericInterfaces()[index];
                }
            });
        }
        return result;
    }

    public static Type[] forTypeVariables(final TypeVariable[] typeVariables) {
        Type[] result = new Type[typeVariables.length];
        for (int i = 0; i < result.length; i++) {
            final int index = i;
            result[i] = forTypeProvider(new DefaultTypeProvider() {
                @Override
                public Type getType() {
                    return typeVariables[index];
                }
            });
        }
        return result;
    }


    @SuppressWarnings("unchecked")
    public static <T extends Type> T unwrap(T type) {
        Type unwrapped = type;
        while (unwrapped instanceof SerializableTypeProxy) {
            unwrapped = ((SerializableTypeProxy) type).getTypeProvider().getType();
        }
        return (T) unwrapped;
    }


}
