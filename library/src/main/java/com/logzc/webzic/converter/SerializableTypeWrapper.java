package com.logzc.webzic.converter;

import com.logzc.webzic.converter.provider.*;
import com.logzc.webzic.util.Assert;
import com.logzc.webzic.web.core.MethodParameter;

import java.io.Serializable;
import java.lang.reflect.*;

/**
 * Created by lishuang on 2016/8/22.
 */
abstract public class SerializableTypeWrapper {


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
        if (typeProvider.getType() instanceof Serializable || typeProvider.getType() == null) {
            return typeProvider.getType();
        }

        for (Class<?> type : SUPPORTED_SERIALIZABLE_TYPES) {


            //typeA.isAssignableFrom(typeB) means typeA is super class of typeB
            if (type.isAssignableFrom(typeProvider.getType().getClass())) {
                ClassLoader classLoader = typeProvider.getClass().getClassLoader();
                Class<?>[] interfaces = new Class<?>[]{
                        type,
                        SerializableTypeProxy.class,
                        Serializable.class
                };
                InvocationHandler handler = new TypeProxyInvocationHandler(typeProvider);
                return (Type) Proxy.newProxyInstance(classLoader, interfaces, handler);
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

    public static Type[] forTypeParameters(final Class<?> type) {
        Type[] result = new Type[type.getTypeParameters().length];
        for (int i = 0; i < result.length; i++) {
            final int index = i;
            result[i] = forTypeProvider(new DefaultTypeProvider() {
                @Override
                public Type getType() {
                    return type.getTypeParameters()[index];
                }
            });
        }
        return result;
    }

    interface SerializableTypeProxy {
        TypeProvider getTypeProvider();
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
