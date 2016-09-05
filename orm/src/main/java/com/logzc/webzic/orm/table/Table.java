package com.logzc.webzic.orm.table;

import com.logzc.webzic.orm.field.ColumnStrategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by lishuang on 2016/8/23.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {

    String name() default "";

    //by default camel(Pojo) to underscore(db).
    ColumnStrategy columnStrategy() default ColumnStrategy.CAMEL_TO_UNDERSCORE;

}
