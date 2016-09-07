package com.logzc.webzic.converter.resolver;

import com.logzc.webzic.converter.ResolvableType;

import java.lang.reflect.TypeVariable;

/**
 * Created by lishuang on 2016/9/7.
 */
public interface VariableResolver {

    Object getSource();

    ResolvableType resolve(TypeVariable<?> variable);
}
