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

        Type providerType=this.provider.getType();

        if (method.getName().equals("equals")) {
            Object other = args[0];
            if (other instanceof Type) {
                other = TypeWrapper.unwrap((Type) other);
            }
            return providerType.equals(other);
        } else if (method.getName().equals("hashCode")) {
            return providerType.hashCode();
        } else if (method.getName().equals("getTypeProvider")) {
            return this.provider;
        }


        //ParameterizedType: Type getRawType(); Type getOwnerType();
        //WildcardType: Type[] getUpperBounds(); Type[] getLowerBounds();
        //GenericArrayType: Type getGenericComponentType();
        //TypeVariable: Type[] getBounds();
        if (Type.class == method.getReturnType() && args == null) {
            MethodInvokeTypeProvider methodProvider=new MethodInvokeTypeProvider(this.provider, method, -1);
            return TypeWrapper.forTypeProvider(methodProvider);
        } else if (Type[].class == method.getReturnType() && args == null) {

            Type[] typeResults = (Type[]) method.invoke(providerType);


            //wrap a coat here.
            Type[] finalResult = new Type[typeResults.length];

            for (int i = 0; i < finalResult.length; i++) {
                MethodInvokeTypeProvider methodProvider=new MethodInvokeTypeProvider(this.provider, method, i);
                finalResult[i] = TypeWrapper.forTypeProvider(methodProvider);
            }
            return finalResult;
        }

        try {
            return method.invoke(providerType, args);
        } catch (InvocationTargetException ex) {
            throw ex.getTargetException();
        }

    }
}
