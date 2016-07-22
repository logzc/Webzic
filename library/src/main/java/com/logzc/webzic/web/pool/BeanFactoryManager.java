package com.logzc.webzic.web.pool;

import com.logzc.webzic.reflection.Reflections;

/**
 * Created by lishuang on 2016/7/19.
 */
public class BeanFactoryManager {

    private static ControllerBeanFactory controllerBeanFactory =new ControllerBeanFactory();



    public static void init(){
        Reflections.scan(controllerBeanFactory.getScanner());

        controllerBeanFactory.postInit();

    }


}
