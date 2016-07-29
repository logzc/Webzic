package com.logzc.webzic.reflection.parameter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by lishuang on 2016/7/29.
 */
public interface ParameterNameFinder {
    List<String> get(Method method);

    List<String>  get(Constructor constructor);
}
