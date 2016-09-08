package com.logzc.webzic.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by lishuang on 2016/9/8.
 */
public class ReflectionUtil {


    /**
     * Recursive search the method up to Object.
     */
    public static Field findField(Class<?> clazz, String name) {
        return findField(clazz, name, null);
    }

    public static Field findField(Class<?> clazz, String name, Class<?> type) {

        Assert.notNull(clazz, "Class must not be null.");

        Assert.isTrue(name != null || type != null, "Either name or type of the field must null be null.");

        Class<?> searchType = clazz;

        while (Object.class != searchType && searchType != null) {


            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                if (name == null) {
                    if (type.equals(field.getType())) {
                        return field;
                    }
                } else {
                    if (name.equals(field.getName())) {
                        return field;
                    }
                }
            }
            searchType = searchType.getSuperclass();
        }

        return null;

    }


    public static Method findMethod(Class<?> clazz, String name) {
        return findMethod(clazz, name, new Class<?>[0]);
    }


    public static Method findMethod(Class<?> clazz, String name, Class<?>... paramTypes) {
        Assert.notNull(clazz, "Class cannot be null.");
        Assert.notNull(name, "Method name cannot be null.");

        Class<?> searchType = clazz;
        while (searchType != null) {
            Method[] methods;
            if (searchType.isInterface()) {
                methods = searchType.getMethods();
            } else {
                methods = searchType.getDeclaredMethods();
            }

            for (Method method : methods) {
                //name equal.
                if (name.equals(method.getName())) {
                    //parameter type equal.
                    if (paramTypes == null || Arrays.equals(paramTypes, method.getParameterTypes())) {
                        return method;
                    }
                }
            }
            searchType = searchType.getSuperclass();
        }
        return null;

    }

    public static <T> Constructor<T> findConstructor(Class<T> clazz, Class<?>... paramTypes) {
        Assert.notNull(clazz, "Class must not be null");
        try {
            return clazz.getConstructor(paramTypes);
        }
        catch (NoSuchMethodException ex) {
            return null;
        }
    }


}
