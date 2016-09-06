package com.logzc.webzic.converter.provider;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created by lishuang on 2016/9/6.
 */

//ParameterizedType: Type getRawType(); Type getOwnerType();
//WildcardType: Type[] getUpperBounds(); Type[] getLowerBounds();
//GenericArrayType: Type getGenericComponentType();
public class MethodInvokeTypeProvider implements TypeProvider {

    private final TypeProvider provider;

    private final String methodName;

    private final int index;

    private transient Method method;

    private transient volatile Object result;

    public MethodInvokeTypeProvider(TypeProvider provider, Method method, int index) {
        this.provider = provider;
        this.methodName = method.getName();
        this.index = index;
        this.method = method;
    }

    @Override
    public Type getType() {
        Object result = this.result;
        if (result == null) {
            // Lazy invocation of the target method on the provided type
            try {
                result = method.invoke(this.provider.getType());
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Cache the result for further calls to getType()
            this.result = result;
        }
        return (result instanceof Type[] ? ((Type[]) result)[this.index] : (Type) result);
    }

    @Override
    public Object getSource() {
        return null;
    }


}