package com.logzc.webzic.converter;

import com.logzc.webzic.util.Assert;
import com.logzc.webzic.util.ClassUtil;
import com.logzc.webzic.web.core.MethodParameter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Some times we need to describe the convert rules of a Type.
 * The target may be a field or a parameter. Not only a Class<?>
 * Created by lishuang on 2016/8/16.
 */
public class TypeDescriptor {

    private static final Map<Class<?>, TypeDescriptor> commonTypeCache = new HashMap<>(18);

    //basic types.
    private static final Class<?>[] BASIC_TYPES = {
            boolean.class, Boolean.class, byte.class, Byte.class, char.class, Character.class,
            double.class, Double.class, int.class, Integer.class, long.class, Long.class,
            float.class, Float.class, short.class, Short.class, String.class, Object.class
    };

    static {
        for (Class<?> clazz : BASIC_TYPES) {
            commonTypeCache.put(clazz, valueOf(clazz));
        }
    }


    private final Class<?> type;
    private final ResolvableType resolvableType;
    private final Annotation[] annotations;


    public TypeDescriptor(ResolvableType resolvableType, Class<?> type, Annotation[] annotations) {
        this.resolvableType = resolvableType;
        if (type == null) {
            this.type = resolvableType.resolve(Object.class);
        } else {
            this.type = type;
        }

        this.annotations = nullSafe(annotations);
    }

    public TypeDescriptor(Field field) {

        Assert.notNull(field, "Field must not be null.");

        this.resolvableType = ResolvableType.forField(field);

        this.type = this.resolvableType.resolve(field.getType());

        this.annotations = nullSafe(field.getAnnotations());
    }

    public TypeDescriptor(MethodParameter methodParameter) {
        Assert.notNull(methodParameter, "MethodParamter cannot be null.");

        this.resolvableType = ResolvableType.forMethodParameter(methodParameter);
        this.type = this.resolvableType.resolve(methodParameter.getParameterType());
        this.annotations = (methodParameter.getParameterIndex() == -1 ?
                nullSafe(methodParameter.getMethodAnnotations()) :
                nullSafe(methodParameter.getParameterAnnotations()));

    }


    public static TypeDescriptor forObject(Object source) {

        if (source == null) {
            return null;
        }

        return valueOf(source.getClass());
    }

    public static TypeDescriptor forMethodParameter(MethodParameter methodParameter) {

        if (methodParameter == null) {
            return null;
        }

        return new TypeDescriptor(methodParameter);
    }

    public static TypeDescriptor forField(Field field) {

        if (field == null) {
            return null;
        }

        return new TypeDescriptor(field);
    }



    public static TypeDescriptor valueOf(Class<?> type) {
        if (type == null) {
            type = Object.class;
        }
        TypeDescriptor descriptor = commonTypeCache.get(type);
        if (descriptor == null) {
            return new TypeDescriptor(ResolvableType.forClass(type), null, null);
        } else {
            return descriptor;
        }
    }


    private Annotation[] nullSafe(Annotation[] annotations) {
        if (annotations == null) {
            return new Annotation[0];
        } else {
            return annotations;
        }
    }


    // String[] -> String
    public TypeDescriptor getElementTypeDescriptor() {

        if (this.resolvableType.isArray()) {
            return new TypeDescriptor(this.resolvableType.getComponentType(), null, this.annotations);
        }


        ResolvableType collectionType = this.resolvableType.asCollection();
        ResolvableType collectionGenericType = collectionType.getGeneric();
        if (collectionGenericType.resolve() == null) {
            return null;
        }
        return new TypeDescriptor(collectionGenericType, null, this.annotations);
    }


    public Class<?> getType() {
        return this.type;
    }

    public boolean isArray() {
        return getType().isArray();
    }

    public boolean isCollection() {
        return Collection.class.isAssignableFrom(getType());
    }

    public boolean isMap() {
        return Map.class.isAssignableFrom(getType());
    }

    public Class<?> getObjectType() {
        return ClassUtil.resolvePrimitive(getType());
    }

    /**
     * valueOf(String.class).isAssignableTo(valueOf(CharSequence.class)) -> true
     * valueOf(Number.class).isAssignableTo(valueOf(Integer.class)) -> false
     */
    public boolean isAssignableTo(TypeDescriptor typeDescriptor) {

        //List.class.isAssignableFrom(ArrayList.class) -> true.
        //here reverse twice. so typeAssignable has the same boolean with this method.
        boolean typeAssignable = typeDescriptor.getObjectType().isAssignableFrom(getObjectType());

        if (!typeAssignable) {
            return false;
        }

        if (isArray() && typeDescriptor.isArray()) {
            return getElementTypeDescriptor().isAssignableTo(typeDescriptor.getElementTypeDescriptor());
        } else if (isCollection() && typeDescriptor.isCollection()) {


            TypeDescriptor sourceElementTypeDescriptor = getElementTypeDescriptor();
            TypeDescriptor targetElementTypeDescriptor = typeDescriptor.getElementTypeDescriptor();

            if (sourceElementTypeDescriptor == null || targetElementTypeDescriptor == null) {
                return true;
            }

            return sourceElementTypeDescriptor.isAssignableTo(targetElementTypeDescriptor);

        }
        //ignore the map type.
        else {
            return true;
        }
    }
}
