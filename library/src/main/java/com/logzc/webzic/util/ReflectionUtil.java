package com.logzc.webzic.util;

import java.lang.reflect.Field;

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


}
