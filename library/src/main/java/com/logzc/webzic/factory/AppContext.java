package com.logzc.webzic.factory;

import com.logzc.webzic.factory.anno.AnnotationBeanFactory;
import com.logzc.webzic.factory.anno.ControllerAnnotationBeanFactory;
import com.logzc.webzic.reflection.Reflections;
import com.logzc.webzic.reflection.scanner.Scanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Factory bean manager.
 * Created by lishuang on 2016/7/19.
 */

public class AppContext {


    static List<AnnotationBeanFactory> annotationBeanFactoryList = new ArrayList<>();
    static Map<Class<? extends AnnotationBeanFactory>, AnnotationBeanFactory> annotationBeanFactoryMap = new HashMap<>();


    static List<BeanFactory> beanFactoryList = new ArrayList<>();
    static Map<Class<? extends BeanFactory>, BeanFactory> beanFactoryMap = new HashMap<>();

    static {


        //register all the bean factories here.

        annotationBeanFactoryList.add(new ControllerAnnotationBeanFactory());

        beanFactoryList.add(new WidgetBeanFactory());


        //create map index.
        annotationBeanFactoryList.forEach(annotationBeanFactory -> {
            annotationBeanFactoryMap.put(annotationBeanFactory.getClass(), annotationBeanFactory);
        });

        beanFactoryList.forEach(beanFactory -> {
            beanFactoryMap.put(beanFactory.getClass(), beanFactory);
        });


    }


    public static void init() {

        //init normal beanFactories.
        beanFactoryList.forEach(BeanFactory::init);


        List<Scanner> scannerList = annotationBeanFactoryList.stream().map(AnnotationBeanFactory::getScanner).collect(Collectors.toList());

        //Guarantee scan once.
        Reflections.scan(scannerList);

        annotationBeanFactoryList.forEach(AnnotationBeanFactory::postInit);


    }


    public static ControllerAnnotationBeanFactory getControllerAnnotationBeanFactory() {

        return getAnnotationBeanFactory(ControllerAnnotationBeanFactory.class);
    }

    @SuppressWarnings("unchecked")
    public static <T extends BeanFactory> T getBeanFactory(Class<T> clazz) {

        return (T) beanFactoryMap.get(clazz);

    }

    @SuppressWarnings("unchecked")
    public static <T extends AnnotationBeanFactory> T getAnnotationBeanFactory(Class<T> clazz) {

        return (T) annotationBeanFactoryMap.get(clazz);

    }


    public static <T> T getBean(Class<T> clazz) {

        for (BeanFactory beanFactory : beanFactoryList) {
            T obj = beanFactory.getBean(clazz);
            if (obj != null) {
                return obj;
            }
        }

        for (AnnotationBeanFactory beanFactory : annotationBeanFactoryList) {
            T obj = beanFactory.getBean(clazz);
            if (obj != null) {
                return obj;
            }
        }


        return null;
    }


}
