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
@SuppressWarnings("unchecked")
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
            annotationBeanFactoryMap.put(annotationBeanFactory.getClass(),annotationBeanFactory);
        });

        beanFactoryList.forEach(beanFactory -> {
            beanFactoryMap.put(beanFactory.getClass(),beanFactory);
        });



    }


    public static void init() {

        List<Scanner> scannerList = annotationBeanFactoryList.stream().map(AnnotationBeanFactory::getScanner).collect(Collectors.toList());

        Reflections.scan(scannerList);

        annotationBeanFactoryList.forEach(AnnotationBeanFactory::postInit);


    }


    public static ControllerAnnotationBeanFactory getControllerAnnotationBeanFactory() {

        return getAnnotationBeanFactory(ControllerAnnotationBeanFactory.class);
    }

    public static <T extends BeanFactory> T getBeanFactory(Class<T> clazz) {

        return (T) beanFactoryMap.get(clazz);

    }

    public static <T extends AnnotationBeanFactory> T getAnnotationBeanFactory(Class<T> clazz) {

        return (T) annotationBeanFactoryMap.get(clazz);

    }


    public static <T> T getBean(Class<T> clazz) {

        return null;
    }


}
