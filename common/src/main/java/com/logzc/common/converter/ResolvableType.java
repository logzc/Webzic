package com.logzc.common.converter;

import com.logzc.common.util.Assert;
import com.logzc.common.util.ObjectUtil;
import com.logzc.common.util.StringUtil;

import java.lang.reflect.*;
import java.util.Collection;
import java.util.Map;

/**
 * encapsulate a Type.
 * Obtained from Field,Method Parameter,Method Return Type,Class
 * Created by lishuang on 2016/8/16.
 */
/*
//Target:

private HashMap<Integer, List<String>> myMap;

   public void example() {
       ResolvableType t = ResolvableType.forField(getClass().getDeclaredField("myMap"));
       t.getSuperType(); // AbstractMap<Integer, List<String>>
       t.asMap(); // Map<Integer, List<String>>
       t.getGeneric(0).resolve(); // Integer
       t.getGeneric(1).resolve(); // List
       t.getGeneric(1); // List<String>
       t.resolveGeneric(1, 0); // String
   }


 */
public class ResolvableType {

    public static final ResolvableType NONE = new ResolvableType(null);

    private final Type type;

    private final Class<?> resolved;

    //ArrayList<Integer> -> List<E> -> E->Integer.
    private final VariableResolver variableResolver;

    private ResolvableType superType;

    private ResolvableType[] interfaces;

    private ResolvableType[] generics;


    public ResolvableType(Type type) {

        this(type, null);
    }

    public ResolvableType(Type type, VariableResolver variableResolver) {

        this.variableResolver = variableResolver;

        if (type == null) {

            this.type = null;
            this.resolved = resolveClass();

        } else if (type instanceof Class) {

            this.resolved = (Class<?>) type;
            this.type = this.resolved;
        } else {
            this.type = type;
            this.resolved = resolveClass();
        }

    }


    public static ResolvableType forType(Type type) {
        return forType(type, null);
    }

    public static ResolvableType forType(Type type, VariableResolver variableResolver) {
        //return forType(type,null,null);
        if (type == null) {
            return NONE;
        }

        return new ResolvableType(type, variableResolver);
    }


    public static ResolvableType forField(Field field) {
        Assert.notNull(field, "Field must not be null.");

        return forType(field.getGenericType());
    }




    //For List<?> , the generic can get from TypeResolver.
    public static ResolvableType[] forTypes(Type[] types, VariableResolver variableResolver) {
        ResolvableType[] result = new ResolvableType[types.length];
        for (int i = 0; i < types.length; i++) {
            result[i] = forType(types[i], variableResolver);
        }
        return result;
    }

    public static ResolvableType forClass(Class<?> sourceClass) {
        return new ResolvableType(sourceClass);
    }


    private Class<?> resolveClass() {

        if (this.type instanceof Class || this.type == null) {
            return (Class<?>) this.type;
        }

        //TODO:Don't know how to do this yet.
        if (this.type instanceof GenericArrayType) {
            return null;
        }

        ResolvableType tempType = resolveType();

        //this.generics=tempType.getGenerics();

        return tempType.resolve();
    }

    private Type resolveBounds(Type[] bounds) {

        if (ObjectUtil.isEmpty(bounds) || Object.class == bounds[0]) {
            return null;
        }
        return bounds[0];
    }


    //erase the generic. List<String> -> List
    //Try temp resolve.
    private ResolvableType resolveType() {

        //eg.this.type -> Base<Integer,String>
        if (this.type instanceof ParameterizedType) {

            //eg. tempType -> Base
            Type tempType = ((ParameterizedType) this.type).getRawType();
            return forType(tempType, this.variableResolver);

        }

        //eg. this.type -> Base<? extend Table>
        if (this.type instanceof WildcardType) {

            // ? extends T  (T is Upper Bounds, default Object.)
            Type resolvedBoundType = resolveBounds(((WildcardType) this.type).getUpperBounds());
            if (resolvedBoundType == null) {

                resolvedBoundType = resolveBounds(((WildcardType) this.type).getLowerBounds());
            }

            return forType(resolvedBoundType, this.variableResolver);

        }

        if (this.type instanceof TypeVariable) {
            TypeVariable<?> typeVariable = (TypeVariable<?>) this.type;

            //try to resolve TypeVariable
            if (this.variableResolver != null) {
                ResolvableType resolvableType = this.variableResolver.resolveVariable(typeVariable);
                if (resolvableType != null) {
                    return resolvableType;
                }
            }

            //Fallback to Bounds.
            return forType(resolveBounds(typeVariable.getBounds()), this.variableResolver);

        }

        return NONE;

    }

    public Class<?> resolve() {
        return resolve(null);
    }

    //This is the key method. fallback is a backup.
    public Class<?> resolve(Class<?> fallback) {
        return this.resolved != null ? this.resolved : fallback;
    }



    public ResolvableType[] getInterfaces() {

        Class<?> resolved = resolve();

        if (resolved == null || ObjectUtil.isEmpty(resolved.getGenericInterfaces())) {
            return new ResolvableType[0];
        }

        //lazy load.
        if (this.interfaces == null) {


            this.interfaces = forTypes(resolved.getGenericInterfaces(), asVariableResolver());

        }
        return this.interfaces;
    }

    public ResolvableType getSuperType() {

        Class<?> resolved = resolve();

        if (resolved == null || resolved.getGenericSuperclass() == null) {
            return NONE;
        }

        if (this.superType == null) {
            this.superType = forType(resolved.getGenericSuperclass());
        }


        return this.superType;
    }


    public ResolvableType asCollection() {
        return as(Collection.class);
    }


    public ResolvableType asMap() {
        return as(Map.class);
    }

    //Convert the type.
    public ResolvableType as(Class<?> type) {
        if (this == NONE) {
            return NONE;
        }

        //No need to convert.
        if (ObjectUtil.equals(resolve(), type)) {
            return this;
        }

        //try super interfaces.
        ResolvableType[] thisInterfaces = getInterfaces();
        for (ResolvableType interfaceType : thisInterfaces) {


            //Recursive here.
            ResolvableType interfaceAsType = interfaceType.as(type);

            if (interfaceAsType != NONE) {
                return interfaceAsType;
            }

        }

        return getSuperType().as(type);


    }

    public VariableResolver asVariableResolver() {
        if (this == NONE) {
            return null;
        }
        return new DefaultVariableResolver(this);
    }

    /**
     * Map<Integer, List<String>>
     * getGeneric(0) -> Integer
     * getGeneric(1,0) -> String
     *
     * @param indexes
     * @return
     */
    public ResolvableType getGeneric(int... indexes) {

        try {
            if (indexes == null || indexes.length == 0) {
                return getGenerics()[0];
            }

            ResolvableType generic = this;
            for (int index : indexes) {
                generic = generic.getGenerics()[index];
            }
            return generic;
        } catch (IndexOutOfBoundsException e) {
            return NONE;
        }


    }

    public ResolvableType[] getGenerics() {

        if (this == NONE) {
            return new ResolvableType[0];
        }

        if (this.generics == null) {

            //Base<M,N>
            if (this.type instanceof Class) {
                Class<?> typeClass = (Class<?>) this.type;


                this.generics = forTypes(typeClass.getTypeParameters(), this.variableResolver);
            } else if (this.type instanceof ParameterizedType) {

                Type[] actualTypeArguments = ((ParameterizedType) this.type).getActualTypeArguments();

                ResolvableType[] generics = new ResolvableType[actualTypeArguments.length];
                for (int i = 0; i < actualTypeArguments.length; i++) {
                    generics[i] = forType(actualTypeArguments[i], this.variableResolver);
                }

                this.generics = generics;

            } else {
                this.generics = resolveType().getGenerics();
            }
        }

        return this.generics;
    }

    //String[] T[] -> true.
    //List<String> -> false.
    public boolean isArray() {
        if (this == NONE) {
            return false;
        }

        boolean b1 = (this.type instanceof Class) && ((Class) this.type).isArray();

        if (b1) {
            //String[]
            return true;
        } else {
            //T[]
            boolean b2 = this.type instanceof GenericArrayType;

            if (b2) {
                return true;
            } else {
                boolean b3 = resolveType().isArray();

                return b3;
            }
        }


    }

    public ResolvableType getComponentType() {

        if (this == NONE) {
            return NONE;
        }

        if (!this.isArray()) {
            return NONE;
        }


        if (this.type instanceof Class) {

            Class<?> componentType = ((Class) this.type).getComponentType();

            return forType(componentType);
        }

        if (this.type instanceof GenericArrayType) {

            Type genericComponentType = ((GenericArrayType) this.type).getGenericComponentType();

            return forType(genericComponentType);
        }

        return resolveType().getComponentType();

    }


    //List<E> -> E -> ArrayList<Integer> -> Integer
    public ResolvableType resolveVariable(TypeVariable<?> variable) {
        if (this.type instanceof TypeVariable) {
            return resolveType().resolveVariable(variable);
        }

        if (this.type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) this.type;

            TypeVariable<?>[] variables = resolve().getTypeParameters();

            for (int i = 0; i < variables.length; i++) {
                if (ObjectUtil.equals(variables[i].getName(), variable.getName())) {
                    Type actualType = parameterizedType.getActualTypeArguments()[i];
                    return forType(actualType, this.variableResolver);
                }
            }

            //try to use owner variable resolver.
            if (parameterizedType.getOwnerType() != null) {
                return forType(parameterizedType.getOwnerType(), this.variableResolver).resolveVariable(variable);
            }
        }

        if (this.variableResolver != null) {
            return this.variableResolver.resolveVariable(variable);
        }
        return null;
    }


    @Override
    public String toString() {
        if (isArray()) {
            return getComponentType() + "[]";
        }
        if (this.resolved == null) {
            return "?";
        }
        if (this.type instanceof TypeVariable) {
            TypeVariable<?> variable = (TypeVariable<?>) this.type;

            if (this.variableResolver == null || this.variableResolver.resolveVariable(variable) == null) {
                return "?";
            }
        }
        StringBuilder result = new StringBuilder(this.resolved.getName());
        ResolvableType[] generics = getGenerics();

        if (generics.length > 0) {
            result.append('<');
            result.append(StringUtil.arrayToString(generics, ", "));
            result.append('>');
        }
        return result.toString();
    }
}
