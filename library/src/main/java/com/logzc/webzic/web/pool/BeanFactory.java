package com.logzc.webzic.web.pool;

import com.logzc.webzic.reflection.scanner.Scanner;
import com.logzc.webzic.util.ClassUtil;

/**
 * Created by lishuang on 2016/7/19.
 */
public abstract class BeanFactory {
    public abstract void postInit();


    //set a default classloader.
    public ClassLoader getClassLoader(){
        return ClassUtil.getClassLoader();
    }
    protected Scanner scanner;
    public abstract Scanner getScanner();

    public abstract Object getBean(Class<?> clazz);
    public abstract Object getBean(String className);

}
