package com.logzc.webzic.bean.factory.anno;

import com.logzc.webzic.annotation.RestController;
import com.logzc.webzic.bean.AppContext;
import com.logzc.webzic.bean.processor.BeanProcessor;
import com.logzc.webzic.reflection.scanner.Scanner;
import com.logzc.webzic.reflection.scanner.TypeAnnotationScanner;
import com.logzc.webzic.web.controller.ExceptionController;
import com.logzc.webzic.web.controller.WebzicPath;
import com.logzc.webzic.web.core.HandlerMethod;
import com.logzc.webzic.web.core.HandlerMethodManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * This is a pool containers all the @Controller.
 * Created by lishuang on 2016/7/19.
 */
public class ControllerAnnotationBeanFactory extends AbstractAnnotationBeanFactory {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * when finish the necessary things. init the request mapping.
     */
    @Override
    public void postInit() throws Exception {


        //find all the methods with @RequestMapping annotation.
        List<String> classNames = scanner.getClassNames();

        HandlerMethodManager handlerMethodManager = AppContext.getHandlerMethodManager();

        //create the indexes of the canned controller.
        for (String className : classNames) {
            Class<?> controllerClass = this.getClassLoader().loadClass(className);
            classes.add(controllerClass);
            beanMap.put(controllerClass, controllerClass.newInstance());
        }


        //query exception handler override.
        HandlerMethod exceptionHandlerMethod = handlerMethodManager.get(WebzicPath.WEBZIC_EXCEPTION);
        if (exceptionHandlerMethod == null) {
            Class<?> controllerClass = ExceptionController.class;
            classes.add(controllerClass);
            beanMap.put(controllerClass, handlerMethodManager.getDefaultExceptionController());
        }

        //process the bean processors.
        for (BeanProcessor beanProcessor : beanProcessors) {

            for (Map.Entry<Class<?>, Object> entry : beanMap.entrySet()) {

                beanProcessor.afterInit(entry.getValue(), entry.getKey());

            }
        }
    }


    @Override
    public Scanner getScanner() {

        if (scanner == null) {
            scanner = new TypeAnnotationScanner(RestController.class);
        }

        return scanner;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getBean(Class<T> clazz) {

        return (T) beanMap.get(clazz);
    }


}
