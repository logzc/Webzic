package com.logzc.webzic.converter;

/**
 * Created by lishuang on 2016/8/4.
 */
public class StringToCharacterConverter implements Converter<String, Character> {
    @Override
    public Character convert(String source) {
        if (source.length() == 0) {
            return null;
        }
        if (source.length() > 1) {
            throw new IllegalArgumentException(
                    "Can only convert a [String] with length of 1 to a [Character]; string value '" + source + "'  has length of " + source.length());
        }
        return source.charAt(0);
    }
}
