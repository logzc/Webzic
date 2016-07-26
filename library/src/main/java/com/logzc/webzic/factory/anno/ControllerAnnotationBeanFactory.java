package com.logzc.webzic.factory.anno;

import com.logzc.webzic.annotation.RequestMapping;
import com.logzc.webzic.annotation.RestController;
import com.logzc.webzic.exception.ZicException;
import com.logzc.webzic.reflection.scanner.Scanner;
import com.logzc.webzic.reflection.scanner.TypeAnnotationScanner;
import com.logzc.webzic.web.core.HandlerMethod;
import com.logzc.webzic.web.core.RequestMethod;
import com.logzc.webzic.web.controller.ErrorController;
import com.logzc.webzic.web.controller.ExceptionController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;

/**
 * This is a pool containers all the @Controller.
 * Created by lishuang on 2016/7/19.
 */
public class ControllerAnnotationBeanFactory extends AbstractAnnotationBeanFactory {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    private List<HandlerMethod> handlerMethodList = new ArrayList<>();
    private Map<String, HandlerMethod> handlerMethodMap = new HashMap<>();


    /**
     * when finish the necessary things. init the request mapping.
     */
    @Override
    public void postInit() {


        //find all the methods with @RequestMapping annotation.
        List<String> classNames = scanner.getClassNames();


        try {

            //create the indexes of the canned controller.
            for (String className : classNames) {
                Class<?> controllerClass = this.getClassLoader().loadClass(className);
                classes.add(controllerClass);
                beanMap.put(controllerClass, controllerClass.newInstance());
                initController(controllerClass);
            }

            //query error handler override
            HandlerMethod errorHandlerMethod = handlerMethodMap.get("/webzic/error");
            if (errorHandlerMethod == null) {

                Class<?> controllerClass = ErrorController.class;
                classes.add(controllerClass);
                beanMap.put(controllerClass, controllerClass.newInstance());
                initController(controllerClass);

            }

            //query exception handler override.
            HandlerMethod exceptionHandlerMethod = handlerMethodMap.get("/webzic/exception");
            if (exceptionHandlerMethod == null) {
                Class<?> controllerClass = ExceptionController.class;
                classes.add(controllerClass);
                beanMap.put(controllerClass, controllerClass.newInstance());
                initController(controllerClass);
            }


        } catch (Exception e) {
            throw new ZicException(e.getMessage());
        }


    }


    /**
     * Read the annotations of the controller class and create the map index.
     */
    public void initController(Class<?> controllerClass) {

        RequestMapping controllerRequestMapping = controllerClass.getAnnotation(RequestMapping.class);
        //this is the base path.
        List<String> controllerRequestPaths = new ArrayList<>();
        //this is the base methods.
        List<RequestMethod> controllerRequestMethods = new ArrayList<>();
        if (controllerRequestMapping != null) {
            String[] paths = controllerRequestMapping.path();
            Collections.addAll(controllerRequestPaths, paths);
            RequestMethod[] requestMethods = controllerRequestMapping.method();
            Collections.addAll(controllerRequestMethods, requestMethods);
        }


        Method[] methods = controllerClass.getMethods();

        for (Method method : methods) {

            if (method.isAnnotationPresent(RequestMapping.class)) {


                Set<String> methodRequestPaths = new HashSet<>();
                Set<RequestMethod> methodRequestMethods = new HashSet<>();


                RequestMapping methodRequestMapping = method.getAnnotation(RequestMapping.class);

                Collections.addAll(methodRequestPaths, methodRequestMapping.path());
                Collections.addAll(methodRequestMethods, methodRequestMapping.method());


                Set<String> finalPaths = new HashSet<>();
                Set<RequestMethod> finalRequestMethods = new HashSet<>();

                //combine the paths.
                for (String path2 : methodRequestPaths) {

                    for (String path1 : controllerRequestPaths) {

                        finalPaths.add(path1 + path2);

                    }
                }

                //combine the request methods.
                finalRequestMethods.addAll(controllerRequestMethods);
                finalRequestMethods.addAll(methodRequestMethods);

                HandlerMethod handlerMethod = new HandlerMethod(getBean(controllerClass), method, finalPaths, finalRequestMethods);
                handlerMethodList.add(handlerMethod);
                logger.debug("Mapped " + handlerMethod);

                for (String path : finalPaths) {
                    handlerMethodMap.put(path, handlerMethod);
                }

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

    @Override
    public Object getBean(Class<?> clazz) {

        return beanMap.get(clazz);
    }

    @Override
    public Object getBean(String className) {

        try {
            Class<?> clazz = getClassLoader().loadClass(className);
            return getBean(clazz);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }


    public HandlerMethod getErrorHandlerMethod() {

        return handlerMethodMap.get("/webzic/error");
    }

    public HandlerMethod getExceptionHandlerMethod() {
        return handlerMethodMap.get("/webzic/exception");
    }


    //get the HandlerMethod by HttpServletRequest.
    public HandlerMethod getHandlerMethod(HttpServletRequest request) {


        String path = request.getRequestURI();

        HandlerMethod handlerMethod = handlerMethodMap.get(path);


        //TODO: pass parameters into the controller.
        if (handlerMethod == null) {
            return getErrorHandlerMethod();
        }

        //check the request methods
        boolean b = handlerMethod.matchRequestMethod(request);
        if (b) {
            return handlerMethod;
        } else {

            return getErrorHandlerMethod();

        }


    }
}
