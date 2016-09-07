package com.logzc.webzic.converter.provider;

import com.logzc.webzic.converter.TypeWrapper;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created by lishuang on 2016/9/6.
 */
public class TypeProxyInvocationHandler implements InvocationHandler, Serializable {
    private final TypeProvider provider;

    public TypeProxyInvocationHandler(TypeProvider typeProvider) {
        this.provider = typeProvider;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.getName().equals("equals")) {
            Object other = args[0];
            if (other instanceof Type) {
                other = TypeWrapper.unwrap((Type) other);
            }
            return this.provider.getType().equals(other);
        } else if (method.getName().equals("hashCode")) {
            return this.provider.getType().hashCode();
        } else if (method.getName().equals("getTypeProvider")) {
            return this.provider;
        }


        //ParameterizedType: Type getRawType(); Type getOwnerType();
        //WildcardType: Type[] getUpperBounds(); Type[] getLowerBounds();
        //GenericArrayType: Type getGenericComponentType();
        if (Type.class == method.getReturnType() && args == null) {
            return TypeWrapper.forTypeProvider(new MethodInvokeTypeProvider(this.provider, method, -1));
        } else if (Type[].class == method.getReturnType() && args == null) {
            Type[] result = new Type[((Type[]) method.invoke(this.provider.getType(), args)).length];
            for (int i = 0; i < result.length; i++) {
                result[i] = TypeWrapper.forTypeProvider(new MethodInvokeTypeProvider(this.provider, method, i));
            }
            return result;
        }

        try {
            return method.invoke(this.provider.getType(), args);
        } catch (InvocationTargetException ex) {
            throw ex.getTargetException();
        }

    }
}
