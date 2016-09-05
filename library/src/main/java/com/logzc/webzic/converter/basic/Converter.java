package com.logzc.webzic.converter.basic;

/**
 * Created by lishuang on 2016/8/4.
 */
public interface Converter<S, T> {

    T convert(S source);
}
