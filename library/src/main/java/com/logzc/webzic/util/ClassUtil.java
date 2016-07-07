package com.logzc.webzic.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the class for loading classes.
 * Created by lishuang on 2016/7/7.
 */
public class ClassUtil {
    private static final Logger logger = LoggerFactory.getLogger(ClassUtil.class);

    public static ClassLoader getClassLoader(){
        logger.debug("get ClassLoader");
        return Thread.currentThread().getContextClassLoader();
    }


}
