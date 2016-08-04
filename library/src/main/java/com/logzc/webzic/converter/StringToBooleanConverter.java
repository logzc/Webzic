package com.logzc.webzic.converter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lishuang on 2016/8/4.
 */
public class StringToBooleanConverter implements Converter<String, Boolean> {

    private static final Set<String> trueValues = new HashSet<>(4);
    private static final Set<String> falseValues = new HashSet<>(4);

    static {
        trueValues.add("true");
        trueValues.add("on");
        trueValues.add("yes");
        trueValues.add("1");

        falseValues.add("false");
        falseValues.add("off");
        falseValues.add("no");
        falseValues.add("0");
    }

    @Override
    public Boolean convert(String source) {
        String value = source.trim();
        if ("".equals(value)) {
            return null;
        }

        value = value.toLowerCase();

        if (trueValues.contains(value)) {
            return Boolean.TRUE;
        } else if (falseValues.contains(value)) {
            return Boolean.FALSE;
        } else {
            throw new IllegalArgumentException("Invalid boolean '" + source + "'");
        }
    }
}
