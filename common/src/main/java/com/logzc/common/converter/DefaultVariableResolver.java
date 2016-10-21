package com.logzc.common.converter;

import java.lang.reflect.TypeVariable;

/**
 * Created by lishuang on 2016/9/26.
 */
public class DefaultVariableResolver implements VariableResolver {

    ResolvableType resolvableType;
    public DefaultVariableResolver(ResolvableType resolvableType){
        this.resolvableType=resolvableType;
    }

    @Override
    public Object getSource() {
        return resolvableType;
    }

    @Override
    public ResolvableType resolveVariable(TypeVariable<?> variable) {
        return resolvableType.resolveVariable(variable);
    }
}
