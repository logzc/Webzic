package com.logzc.webzic.factory;

import com.logzc.webzic.factory.anno.ControllerAnnotationBeanFactory;
import com.logzc.webzic.reflection.Reflections;

/**
 * Created by lishuang on 2016/7/19.
 */
public class AppContext implements BeanFactory{


    

    //Beans with annotation @Controller.
    private static ControllerAnnotationBeanFactory controllerBeanFactory = new ControllerAnnotationBeanFactory();

    //Beans needed by the webzic framework.
    private static WidgetBeanFactory widgetBeanFactory = new WidgetBeanFactory();


    public static void init() {


        Reflections.scan(controllerBeanFactory.getScanner());
        controllerBeanFactory.postInit();

    }

    public static ControllerAnnotationBeanFactory getControllerBeanFactory() {
        return controllerBeanFactory;
    }

    public Object getBean(Class<?> clazz){
        return null;
    }
    public Object getBean(String className){
        return null;
    }

}
