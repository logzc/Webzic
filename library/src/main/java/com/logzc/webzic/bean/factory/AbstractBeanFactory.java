package com.logzc.webzic.bean.factory;

import com.logzc.common.util.ClassUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lishuang on 2016/7/19.
 */
public abstract class AbstractBeanFactory implements BeanFactory {


    //contains all the classes.
    protected List<Class<?>> classes = new ArrayList<>();
    //a map contains all the classes.
    protected Map<Class<?>, Object> beanMap = new HashMap<>();


    //set a default classloader.
    public ClassLoader getClassLoader() {
        return ClassUtil.getClassLoader();
    }

    public Map<Class<?>, Object> getBeanMap() {
        return beanMap;
    }

}
