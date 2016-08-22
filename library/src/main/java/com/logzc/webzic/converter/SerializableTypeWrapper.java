package com.logzc.webzic.converter;

import com.logzc.webzic.util.Assert;
import com.logzc.webzic.web.core.MethodParameter;

import java.io.Serializable;
import java.lang.reflect.*;

/**
 * Created by lishuang on 2016/8/22.
 */
abstract public class SerializableTypeWrapper {


    private static final Class<?>[] SUPPORTED_SERIALIZABLE_TYPES = {
            GenericArrayType.class,
            ParameterizedType.class,
            TypeVariable.class,
            WildcardType.class
    };


    public static Type forTypeProvider(TypeProvider typeProvider) {
        Assert.notNull(typeProvider, "Provider must not null.");

        //"type instanceof Serializable" to check whether type is a Generic.
        if (typeProvider.getType() instanceof Serializable || typeProvider.getType() == null) {
            return typeProvider.getType();
        }

        for (Class<?> type : SUPPORTED_SERIALIZABLE_TYPES) {
            if (type.isAssignableFrom(typeProvider.getType().getClass())) {
                ClassLoader classLoader = typeProvider.getClass().getClassLoader();
                Class<?>[] interfaces = new Class<?>[]{
                        type,
                        SerializableTypeProxy.class,
                        Serializable.class
                };
                InvocationHandler handler = new TypeProxyInvocationHandler(typeProvider);
                return (Type) Proxy.newProxyInstance(classLoader, interfaces, handler);
            }
        }
        throw new IllegalArgumentException("Unsupported Type class: " + typeProvider.getType().getClass().getName());
    }

    public static Type forField(Field field) {
        Assert.notNull(field, "Field must not be null.");
        return forTypeProvider(new FieldTypeProvider(field));
    }

    public static Type forMethodParameter(MethodParameter methodParameter) {
        return forTypeProvider(new MethodParameterTypeProvider(methodParameter));
    }

    public static Type forGenericSuperclass(final Class<?> type) {
        return forTypeProvider(new DefaultTypeProvider() {
            @Override
            public Type getType() {
                return type.getGenericSuperclass();
            }
        });
    }

    public static Type[] forGenericInterfaces(final Class<?> type) {
        Type[] result = new Type[type.getGenericInterfaces().length];
        for (int i = 0; i < result.length; i++) {
            final int index = i;
            result[i] = forTypeProvider(new DefaultTypeProvider() {
                @Override
                public Type getType() {
                    return type.getGenericInterfaces()[index];
                }
            });
        }
        return result;
    }

    public static Type[] forTypeParameters(final Class<?> type) {
        Type[] result = new Type[type.getTypeParameters().length];
        for (int i = 0; i < result.length; i++) {
            final int index = i;
            result[i] = forTypeProvider(new DefaultTypeProvider() {
                @Override
                public Type getType() {
                    return type.getTypeParameters()[index];
                }
            });
        }
        return result;
    }

    interface SerializableTypeProxy {
        TypeProvider getTypeProvider();
    }

    interface TypeProvider extends Serializable {

        Type getType();

        Object getSource();
    }


    private static abstract class DefaultTypeProvider implements TypeProvider {
        @Override
        public Object getSource() {
            return null;
        }
    }

    static class FieldTypeProvider implements TypeProvider {

        private final String fieldName;

        private final Class<?> declaringClass;

        private transient Field field;

        public FieldTypeProvider(Field field) {
            this.fieldName = field.getName();
            this.declaringClass = field.getDeclaringClass();
            this.field = field;

        }

        @Override
        public Type getType() {
            return this.field.getGenericType();
        }

        @Override
        public Object getSource() {
            return this.field;
        }

    }


    static class MethodParameterTypeProvider implements TypeProvider {
        private final String methodName;
        private final Class<?>[] parameterTypes;
        private final Class<?> declaringClass;
        private final int parameterIndex;
        private transient MethodParameter methodParameter;

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

    static class MethodInvokeTypeProvider implements TypeProvider {

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


    @SuppressWarnings("unchecked")
    public static <T extends Type> T unwrap(T type) {
        Type unwrapped = type;
        while (unwrapped instanceof SerializableTypeProxy) {
            unwrapped = ((SerializableTypeProxy) type).getTypeProvider().getType();
        }
        return (T) unwrapped;
    }

    private static class TypeProxyInvocationHandler implements InvocationHandler, Serializable {
        private final TypeProvider provider;

        public TypeProxyInvocationHandler(TypeProvider typeProvider) {
            this.provider = typeProvider;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            if (method.getName().equals("equals")) {
                Object other = args[0];
                if (other instanceof Type) {
                    other = unwrap((Type) other);
                }
                return this.provider.getType().equals(other);
            } else if (method.getName().equals("hashCode")) {
                return this.provider.getType().hashCode();
            } else if (method.getName().equals("getTypeProvider")) {
                return this.provider;
            }


            //TODO:Could not quite understand.
            if (Type.class == method.getReturnType() && args == null) {
                return forTypeProvider(new MethodInvokeTypeProvider(this.provider, method, -1));
            } else if (Type[].class == method.getReturnType() && args == null) {
                Type[] result = new Type[((Type[]) method.invoke(this.provider.getType(), args)).length];
                for (int i = 0; i < result.length; i++) {
                    result[i] = forTypeProvider(new MethodInvokeTypeProvider(this.provider, method, i));
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


}
