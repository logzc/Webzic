package com.logzc.common.converter.basic;

import com.logzc.common.util.Assert;

/**
 * Created by lishuang on 2016/8/4.
 */
public class StringToEnumConverterFactory implements ConverterFactory<String, Enum> {

    @Override
    public <T extends Enum> Converter<String, T> getConverter(Class<T> targetClass) {

        Assert.notNull(targetClass,"target class cannot be null");

        if (!targetClass.isEnum()) {
            throw new IllegalArgumentException("The target type " + targetClass.getName() + " does not refer to an enum");
        }

        return new StringToEnum<T>(targetClass);
    }

    private class StringToEnum<T extends Enum> implements Converter<String, T> {

        private final Class<T> enumType;

        public StringToEnum(Class<T> enumType) {
            this.enumType = enumType;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T convert(String source) {

            if (source.length() == 0) {
                return null;
            }


            return (T) Enum.valueOf(this.enumType, source.trim());
        }
    }
}
