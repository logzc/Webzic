package com.logzc.webzic.converter;

import com.logzc.webzic.converter.generic.GenericConverter;
import com.logzc.webzic.util.ClassUtil;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Manage all the converters.
 * Created by lishuang on 2016/9/20.
 */
public class ConverterPool {

    private final Set<GenericConverter> globalConverters = new LinkedHashSet<>();

    private final Map<ConvertiblePair, List<GenericConverter>> converters = new LinkedHashMap<>();

    public void add(GenericConverter converter) {

        Set<ConvertiblePair> convertiblePairs = converter.getConvertiblePairs();

        if (convertiblePairs == null) {
            throw new IllegalArgumentException("Converter has no convertible pair.");
        } else {
            for (ConvertiblePair convertiblePair : convertiblePairs) {
                List<GenericConverter> list = converters.get(convertiblePair);
                if (list == null) {
                    list = new ArrayList<>();
                    this.converters.put(convertiblePair, list);
                }

                list.add(converter);
            }
        }


    }


    public List<Class<?>> getClassHierarchy(Class<?> type) {

        List<Class<?>> hierarchy = new ArrayList<>();
        Set<Class<?>> visited = new HashSet<>();

        addToClassHierarchy(0, ClassUtil.resolvePrimitive(type), false, hierarchy, visited);

        //String[] true. List<String> false.
        boolean array = type.isArray();

        int i = 0;
        while (i < hierarchy.size()) {
            Class<?> candidate = hierarchy.get(i);

            candidate = array ? candidate.getComponentType() : ClassUtil.resolvePrimitive(candidate);

            Class<?> superclass = candidate.getSuperclass();

            if (superclass != null && superclass != Object.class && superclass != Enum.class) {
                addToClassHierarchy(i + 1, superclass, array, hierarchy, visited);
            }

            addInterfaceToClassHierarchy(candidate, array, hierarchy, visited);

            i++;


        }

        if (Enum.class.isAssignableFrom(type)) {
            addToClassHierarchy(hierarchy.size(), Enum.class, array, hierarchy, visited);
            addToClassHierarchy(hierarchy.size(), Enum.class, false, hierarchy, visited);
            addInterfaceToClassHierarchy(Enum.class, array, hierarchy, visited);
        }

        addToClassHierarchy(hierarchy.size(), Object.class, array, hierarchy, visited);
        addToClassHierarchy(hierarchy.size(), Object.class, false, hierarchy, visited);


        return hierarchy;

    }


    private void addInterfaceToClassHierarchy(Class<?> type, boolean asArray, List<Class<?>> hierarchy, Set<Class<?>> visited) {

        for (Class<?> implementedInterface : type.getInterfaces()) {
            addToClassHierarchy(hierarchy.size(), implementedInterface, asArray, hierarchy, visited);
        }
    }

    private void addToClassHierarchy(int index, Class<?> type, boolean asArray, List<Class<?>> hierarchy, Set<Class<?>> visited) {
        if (asArray) {
            type = Array.newInstance(type, 0).getClass();
        }
        if (visited.add(type)) {
            hierarchy.add(index, type);
        }
    }

}
