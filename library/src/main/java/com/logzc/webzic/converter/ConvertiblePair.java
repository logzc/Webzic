package com.logzc.webzic.converter;

import com.logzc.webzic.util.Assert;
import com.logzc.webzic.util.builder.HashCodeBuilders;

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
        return this.sourceType == otherPair.sourceType && this.targetType == otherPair.sourceType;
    }

    @Override
    public int hashCode(){
        return HashCodeBuilders.reflectionHashCode(this);
    }
    @Override
    public String toString(){
        return this.sourceType.getName()+"->"+this.targetType.getName();
    }
}
