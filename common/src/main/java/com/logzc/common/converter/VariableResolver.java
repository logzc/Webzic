package com.logzc.common.converter;

import java.lang.reflect.TypeVariable;

/**
 * Created by lishuang on 2016/9/26.
 */
public interface VariableResolver {

    Object getSource();

    ResolvableType resolveVariable(TypeVariable<?> variable);
}
