package com.logzc.webzic.converter;

import com.logzc.webzic.util.Assert;
import com.logzc.webzic.web.core.MethodParameter;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * Created by lishuang on 2016/8/22.
 */
abstract public class TypeWrapper {

    public static Type forField(Field field) {
        Assert.notNull(field, "Field must not be null.");
        return field.getGenericType();
    }

    public static Type forMethodParameter(MethodParameter methodParameter) {

        return methodParameter.getGenericParameterType();

    }

    public static Type forGenericSuperclass(final Class<?> type) {
        return type.getGenericSuperclass();
    }

    public static Type[] forGenericInterfaces(final Class<?> type) {
        Type[] result = new Type[type.getGenericInterfaces().length];
        for (int i = 0; i < result.length; i++) {
            result[i] = type.getGenericInterfaces()[i];
        }
        return result;
    }


}
