package com.logzc.webzic.orm.field;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * indicate this is a transient field.
 * Created by lishuang on 2016/8/23.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Transient {

    String name() default "";

    boolean id() default false;
}
