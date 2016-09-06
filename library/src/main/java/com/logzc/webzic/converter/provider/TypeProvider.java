package com.logzc.webzic.converter.provider;

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * Created by lishuang on 2016/9/6.
 */
public interface TypeProvider extends Serializable {

    Type getType();

    Object getSource();
}
