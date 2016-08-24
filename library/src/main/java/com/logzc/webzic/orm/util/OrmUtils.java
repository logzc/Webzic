package com.logzc.webzic.orm.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by lishuang on 2016/8/24.
 */
public class OrmUtils {

    //this is a Util method.
    public static Method findGetter(Field field) {
        Class<?> clazz = field.getDeclaringClass();

        String name = field.getName();

        if (name.length() == 0) {
            return null;
        }

        String getterName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
        Method getterMethod;

        try {
            getterMethod = clazz.getMethod(getterName);
        } catch (NoSuchMethodException e) {
            return null;
        }

        return getterMethod;

    }

    //this is a Util method.
    public static Method findSetter(Field field) {
        Class<?> clazz = field.getDeclaringClass();

        String name = field.getName();

        if (name.length() == 0) {
            return null;
        }

        String getterName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
        Method getterMethod;

        try {
            getterMethod = clazz.getMethod(getterName, field.getType());
        } catch (NoSuchMethodException e) {
            return null;
        }

        return getterMethod;

    }
}
