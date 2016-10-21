package com.logzc.webzic.bean;

import com.logzc.webzic.bean.factory.BeanFactory;
import com.logzc.webzic.bean.factory.WidgetBeanFactory;
import com.logzc.webzic.bean.factory.anno.AnnotationBeanFactory;
import com.logzc.webzic.bean.factory.anno.ComponentAnnotationBeanFactory;
import com.logzc.webzic.bean.factory.anno.ControllerAnnotationBeanFactory;
import com.logzc.webzic.bean.factory.anno.RepositoryAnnotationBeanFactory;
import com.logzc.webzic.bean.processor.BeanProcessorManager;
import com.logzc.common.converter.ConversionService;
import com.logzc.webzic.orm.support.ConnectionSource;
import com.logzc.webzic.reflection.Reflections;
import com.logzc.webzic.reflection.scanner.Scanner;
import com.logzc.webzic.web.config.Constants;
import com.logzc.webzic.web.core.HandlerMethodManager;
import com.logzc.webzic.web.core.OutputManager;

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
    static List<BeanFactory> normalBeanFactoryList = new ArrayList<>();
    static Map<Class<? extends BeanFactory>, BeanFactory> normalBeanFactoryMap = new HashMap<>();

    //Important singletons.
    static ConversionService conversionService;
    static HandlerMethodManager handlerMethodManager;
    static OutputManager outputManager;
    static Constants constants;
    static ConnectionSource connectionSource;

    static BeanProcessorManager beanProcessorManager=new BeanProcessorManager();

    static boolean hasInitialized = false;

    static {


        //register all the bean factories here.
        normalBeanFactoryList.add(new WidgetBeanFactory());
        normalBeanFactoryList.forEach(beanFactory -> {
            normalBeanFactoryMap.put(beanFactory.getClass(), beanFactory);
        });

        annotationBeanFactoryList.add(new ControllerAnnotationBeanFactory());
        annotationBeanFactoryList.add(new RepositoryAnnotationBeanFactory());
        annotationBeanFactoryList.add(new ComponentAnnotationBeanFactory());

        annotationBeanFactoryList.forEach(annotationBeanFactory -> {

            //create map index.
            annotationBeanFactoryMap.put(annotationBeanFactory.getClass(), annotationBeanFactory);

        });






    }


    public static void init() throws Exception {

        if (hasInitialized) {
            return;
        } else {
            hasInitialized = true;
        }

        //init normal beanFactories.
        for (BeanFactory beanFactory:normalBeanFactoryList){
            beanFactory.init();
        }

        for (BeanFactory beanFactory:annotationBeanFactoryList){
            beanFactory.init();
        }

        List<Scanner> scannerList = annotationBeanFactoryList.stream().map(AnnotationBeanFactory::getScanner).collect(Collectors.toList());

        //Guarantee scan once.
        Reflections.scan(scannerList);


        for (AnnotationBeanFactory annotationBeanFactory : annotationBeanFactoryList) {
            annotationBeanFactory.postInit();
        }


        for (AnnotationBeanFactory annotationBeanFactory : annotationBeanFactoryList) {
            //execute the processors.
            beanProcessorManager.process(annotationBeanFactory.getBeanMap());
        }

    }


    public static ControllerAnnotationBeanFactory getControllerAnnotationBeanFactory() {

        return getAnnotationBeanFactory(ControllerAnnotationBeanFactory.class);
    }

    @SuppressWarnings("unchecked")
    public static <T extends BeanFactory> T getBeanFactory(Class<T> clazz) {

        return (T) normalBeanFactoryMap.get(clazz);

    }

    @SuppressWarnings("unchecked")
    public static <T extends AnnotationBeanFactory> T getAnnotationBeanFactory(Class<T> clazz) {

        return (T) annotationBeanFactoryMap.get(clazz);

    }


    public static <T> T getBean(Class<T> clazz) {

        for (BeanFactory beanFactory : normalBeanFactoryList) {
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


    public static HandlerMethodManager getHandlerMethodManager() {
        if (handlerMethodManager == null) {
            handlerMethodManager = getBean(HandlerMethodManager.class);
        }
        return handlerMethodManager;
    }

    public static OutputManager getOutputManager() {
        if (outputManager == null) {
            outputManager = getBean(OutputManager.class);
        }
        return outputManager;
    }


    public static Constants getConstants() {
        if (constants == null) {
            constants = getBean(Constants.class);
        }
        return constants;
    }

    public static ConversionService getConversionService() {
        if (conversionService == null) {
            conversionService = getBean(ConversionService.class);
        }
        return conversionService;
    }

    public static ConnectionSource getConnectionSource() {
        if (connectionSource == null) {
            connectionSource = getBean(ConnectionSource.class);
        }
        return connectionSource;
    }



}
