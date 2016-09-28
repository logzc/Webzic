package com.logzc.webzic.bean.factory;

import com.logzc.webzic.converter.ConversionService;
import com.logzc.webzic.converter.DefaultConversionService;
import com.logzc.webzic.bean.factory.anno.AnnotationBeanFactory;
import com.logzc.webzic.bean.factory.anno.ControllerAnnotationBeanFactory;
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


    //for test purpose make it public.
    static List<BeanFactory> beanFactoryList = new ArrayList<>();
    static Map<Class<? extends BeanFactory>, BeanFactory> beanFactoryMap = new HashMap<>();

    //ConversionService.
    static ConversionService conversionService = new DefaultConversionService();

    static boolean hasInitialized = false;

    static {


        //register all the bean factories here.
        beanFactoryList.add(new WidgetBeanFactory());
        beanFactoryList.forEach(beanFactory -> {
            beanFactoryMap.put(beanFactory.getClass(), beanFactory);
        });


        annotationBeanFactoryList.add(new ControllerAnnotationBeanFactory());
        //create map index.
        annotationBeanFactoryList.forEach(annotationBeanFactory -> {
            annotationBeanFactoryMap.put(annotationBeanFactory.getClass(), annotationBeanFactory);
        });


    }


    public static void init() {

        if (hasInitialized) {
            return;
        } else {
            hasInitialized = true;
        }

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

    public static ConversionService getConversionService() {
        return conversionService;
    }


}
