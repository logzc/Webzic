package com.logzc.webzic.converter;

import com.logzc.webzic.util.NumberUtil;

/**
 * Created by lishuang on 2016/8/4.
 */
public class StringToNumberConverterFactory implements ConverterFactory<String, Number> {
    @Override
    public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToNumber<>(targetType);
    }

    private static final class StringToNumber<T extends Number> implements Converter<String, T> {


        private final Class<T> targetClass;

        public StringToNumber(Class<T> targetClass) {
            this.targetClass = targetClass;
        }

        @Override
        public T convert(String source) {
            if (source.length() == 0) {
                return null;
            }
            return NumberUtil.parseNumber(source, targetClass);
        }
    }
}
