package com.logzc.webzic.converter;

/**
 * encapsulate a Type.
 * Obtained from Field,Method Parameter,Method Return Type,Class
 * Created by lishuang on 2016/8/16.
 */
public class ResolvableType {


    private final Class<?> resolved;


    public ResolvableType(Class<?> sourceClass) {

        if (sourceClass == null) {
            this.resolved = Object.class;
        } else {
            this.resolved = sourceClass;
        }

    }

    public static ResolvableType forClass(Class<?> sourceClass) {
        return new ResolvableType(sourceClass);
    }


    //This is the key method. fallback is a backup.
    public Class<?> resolve(Class<?> fallback) {
        return this.resolved != null ? this.resolved : fallback;
    }

}
