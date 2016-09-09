package com.logzc.webzic.converter;

import com.logzc.webzic.util.Assert;

import java.util.*;

/**
 * Created by lishuang on 2016/9/9.
 */
public class CollectionFactory {


    @SuppressWarnings({"unchecked", "cast"})
    public static <E> Collection<E> createCollection(Class<?> collectionType, Class<?> elementType, int capacity) {
        Assert.notNull(collectionType, "Collection type cannot be null.");

        if (collectionType.isInterface()) {

            if (Set.class == collectionType || Collection.class == collectionType) {
                return new LinkedHashSet<E>(capacity);
            } else if (List.class == collectionType) {
                return new ArrayList<E>(capacity);
            } else if (SortedSet.class == collectionType) {
                return new TreeSet<E>();
            } else {
                throw new IllegalArgumentException("Unsupported Collection interface:" + collectionType.getName());
            }

        } else if (EnumSet.class == collectionType) {

            Assert.notNull(elementType, "EnumSet's elementType cannot be null.");

            return (Collection<E>) EnumSet.noneOf(asEnumType(elementType));

        } else {

            if (!Collection.class.isAssignableFrom(collectionType)) {
                throw new IllegalArgumentException("Unsupported Collection type:" + collectionType.getName());

            }
            try {
                return (Collection<E>) collectionType.newInstance();
            } catch (Exception ex) {

                throw new IllegalArgumentException("Could not create Collection Type:" + collectionType.getName());

            }
        }


    }

    private static Class<? extends Enum> asEnumType(Class<?> enumType) {

        Assert.notNull(enumType, "Enum type must not be null");

        if (!Enum.class.isAssignableFrom(enumType)) {
            throw new IllegalArgumentException("Not an enum type:" + enumType.getName());
        }
        return enumType.asSubclass(Enum.class);

    }

}
