package com.logzc.common.util;

import com.logzc.common.converter.ResolvableType;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by lishuang on 2016/7/18.
 */
public class StringUtil {

    public static boolean isNotEmpty(final CharSequence cs) {
        return StringUtils.isNotEmpty(cs);
    }


    public static String trimAllWhitespace(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }

        int length = str.length();

        StringBuilder stringBuilder = new StringBuilder(str.length());

        for (int i = 0; i < length; i++) {
            char c = str.charAt(i);
            if (!Character.isWhitespace(c)) {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }

    public static String[] split(String str, String symbol) {
        return null;
    }

    public static String[] splitByComma(String str) {
        return new String[0];
    }

    public static String arrayToString(ResolvableType[] array, String symbol) {

        if (ObjectUtil.isEmpty(array)) {
            return "";

        }

        if (array.length == 1) {
            return array[0].toString();
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                sb.append(symbol);
            }
            sb.append(array[i]);
        }
        return sb.toString();

    }
}
