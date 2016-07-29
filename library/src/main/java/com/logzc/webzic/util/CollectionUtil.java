package com.logzc.webzic.util;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lishuang on 2016/7/26.
 */
public class CollectionUtil {

    public static boolean isEmpty(final Collection<?> coll) {
        return CollectionUtils.isEmpty(coll);
    }

    public static boolean isNotEmpty(final Collection<?> coll) {
        return CollectionUtils.isNotEmpty(coll);
    }

    public static boolean isEmpty(Map<?, ?> map) {

        return map == null || map.isEmpty();
    }

    public static boolean isEmpty(Object[] array) {
        return (array == null || array.length == 0);
    }


    public static boolean sizeIsEmpty(final Object object) {
        return CollectionUtils.sizeIsEmpty(object);
    }


    //clone a map.
    public static <K, V> Map<K, V> copy(Map<K, V> map) {
        Map<K, V> newMap = new HashMap<>();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            newMap.put(entry.getKey(), entry.getValue());
        }
        return newMap;
    }
}
