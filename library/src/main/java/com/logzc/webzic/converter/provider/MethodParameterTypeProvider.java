package com.logzc.webzic.converter.provider;

import com.logzc.webzic.web.core.MethodParameter;

import java.lang.reflect.Type;

/**
 * Created by lishuang on 2016/9/6.
 */
public class MethodParameterTypeProvider implements TypeProvider {

    private final String methodName;
    private final Class<?>[] parameterTypes;
    private final Class<?> declaringClass;
    private final int parameterIndex;
    private MethodParameter methodParameter;

    public MethodParameterTypeProvider(MethodParameter methodParameter) {
        if (methodParameter.getMethod() != null) {
            this.methodName = methodParameter.getMethod().getName();
            this.parameterTypes = methodParameter.getMethod().getParameterTypes();
        } else {
            this.methodName = null;
            this.parameterTypes = methodParameter.getConstructor().getParameterTypes();
        }
        this.declaringClass = methodParameter.getDeclaringClass();
        this.parameterIndex = methodParameter.getParameterIndex();
        this.methodParameter = methodParameter;
    }

    @Override
    public Type getType() {
        return this.methodParameter.getGenericParameterType();
    }

    @Override
    public Object getSource() {
        return this.methodParameter;
    }

}
