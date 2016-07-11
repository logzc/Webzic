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

    public static Class<?> loadClass(String className,boolean isInitialized){

        Class<?> clazz;
        try{
            clazz=Class.forName(className,isInitialized,getClassLoader());
        }catch (ClassNotFoundException e) {
            logger.debug("Cannot find "+className);
            throw new RuntimeException();
        }
        return clazz;
    }


}
