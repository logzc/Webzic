package com.logzc.webzic.factory;

import com.logzc.webzic.reflection.scanner.Scanner;
import com.logzc.webzic.util.ClassUtil;

/**
 * Created by lishuang on 2016/7/19.
 */
public interface BeanFactory {


    Object getBean(Class<?> clazz);
    Object getBean(String className);

}
