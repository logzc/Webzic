package com.logzc.webzic.converter;

import java.lang.annotation.Annotation;
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


    /*
    public TypeDescriptor(MethodParameter methodParameter){
        Assert.notNull(methodParameter,"MethodParamter cannot be null.");

        this.resolvableType=ResolvableType.forMethodParameter(methodParameter);
        this.type=this.resolvableType.resolve(methodParameter.getParameterType());
        this.annotations=(methodParameter.getParameterIndex()==-1?nullSafe(methodParameter.getMethodAnnotations()):nullSafe(methodParameter.getParameterAnnotations()));

    }
    */

    public TypeDescriptor(ResolvableType resolvableType, Class<?> type, Annotation[] annotations) {
        this.resolvableType = resolvableType;
        if (type == null) {
            this.type = Object.class;
        } else {
            this.type = type;
        }

        this.annotations = nullSafe(annotations);
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


    // List<String> -> java.lang.String
    public TypeDescriptor getElementTypeDescriptor() {

        //TODO: finish the elementTypeDescriptor.
        //return getRelatedResolvable(this, this.resolvableType.asCollection().getGeneric());

        return null;
    }


    private TypeDescriptor getRelatedResolvable(TypeDescriptor source, ResolvableType type) {

        if (type.resolve() == null) {
            return null;
        }

        return new TypeDescriptor(type, null, source.annotations);

    }


}
