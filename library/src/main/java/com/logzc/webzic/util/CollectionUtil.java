package com.logzc.webzic.util;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
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
}
