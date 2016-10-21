package com.logzc.common.converter;

import com.logzc.common.util.Assert;

/**
 * Created by lishuang on 2016/8/5.
 */
public class ConvertiblePair {

    private final Class<?> sourceType;
    private final Class<?> targetType;

    public ConvertiblePair(Class<?> sourceType, Class<?> targetType) {
        Assert.notNull(sourceType);
        Assert.notNull(targetType);
        this.sourceType = sourceType;
        this.targetType = targetType;
    }

    @Override
    public boolean equals(Object other) {

        if (this == other) {
            return true;
        }
        if (other == null || other.getClass() != ConvertiblePair.class) {
            return false;
        }

        ConvertiblePair otherPair = (ConvertiblePair) other;
        return this.sourceType == otherPair.sourceType && this.targetType == otherPair.targetType;
    }

    @Override
    public int hashCode() {
        return this.sourceType.hashCode() * 31 + this.targetType.hashCode();
    }


    @Override
    public String toString() {
        return this.sourceType.getName() + "->" + this.targetType.getName();
    }
}
