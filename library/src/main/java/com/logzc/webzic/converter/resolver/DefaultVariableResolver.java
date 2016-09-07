package com.logzc.webzic.converter.resolver;

import com.logzc.webzic.converter.ResolvableType;

import java.lang.reflect.TypeVariable;

/**
 * Created by lishuang on 2016/9/7.
 */
public class DefaultVariableResolver implements VariableResolver {
    @Override
    public Object getSource() {
        return null;
    }

    @Override
    public ResolvableType resolveVariable(TypeVariable<?> variable) {
        return null;
    }
}
