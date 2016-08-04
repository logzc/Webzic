package com.logzc.webzic.util;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by lishuang on 2016/8/4.
 */
public class NumberUtil {


    @SuppressWarnings("unchecked")
    public static <T extends Number> T parseNumber(String text, Class<T> targetClass) {
        Assert.notNull(text, "Text cannot be null.");
        Assert.notNull(targetClass, "Target class must not be null.");

        String trimmed = StringUtil.trimAllWhitespace(text);

        if (Byte.class == targetClass) {
            return (T) Byte.valueOf(trimmed);
        } else if (Short.class == targetClass) {
            return (T) Short.valueOf(trimmed);
        } else if (Integer.class == targetClass) {
            return (T) Integer.valueOf(trimmed);
        } else if (Long.class == targetClass) {
            return (T) Long.valueOf(trimmed);
        } else if (BigInteger.class == targetClass) {
            return (T) new BigInteger(trimmed);
        } else if (Float.class == targetClass) {
            return (T) Float.valueOf(trimmed);
        } else if (Double.class == targetClass) {
            return (T) Double.valueOf(trimmed);
        } else if (BigDecimal.class == targetClass || Number.class == targetClass) {
            return (T) new BigDecimal(trimmed);
        } else {
            throw new IllegalArgumentException("Cannot convert String [" + text + "] to target class [" + targetClass.getName() + "]");
        }

    }
}
