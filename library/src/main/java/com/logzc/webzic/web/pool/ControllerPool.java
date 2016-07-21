package com.logzc.webzic.web.pool;

import com.logzc.webzic.annotation.RequestMapping;
import com.logzc.webzic.annotation.RestController;
import com.logzc.webzic.dynamic.scanner.Scanner;
import com.logzc.webzic.dynamic.scanner.TypeAnnotationScanner;
import com.logzc.webzic.exception.ZicException;
import com.logzc.webzic.util.ClassUtil;
import com.logzc.webzic.web.HandlerMethod;
import com.logzc.webzic.web.RequestMappingInfo;
import com.logzc.webzic.web.annotation.RequestMethod;

import java.lang.reflect.Method;
import java.util.*;

/**
 * This is a pool containers all the @Controller.
 * Created by lishuang on 2016/7/19.
 */
public class ControllerPool extends Pool {

    private Set<RequestMappingInfo> requestMappingInfos = new HashSet<>();

    private List<Class<?>> classes = new ArrayList<>();
    private List<HandlerMethod> handlerMethodList = new ArrayList<>();
    private List<RequestMappingInfo> requestMappingInfoList = new ArrayList<>();


    /**
     * when finish the necessary things. init the request mapping.
     */
    @Override
    public void postInit() {

        //find all the methods with @RequestMapping annotation.
        List<String> classNames = scanner.getClassNames();

        for (String className : classNames) {
            try {
                Class<?> controllerClass = ClassUtil.getClassLoader().loadClass(className);
                classes.add(controllerClass);

                RequestMapping controllerRequestMapping = controllerClass.getAnnotation(RequestMapping.class);
                List<String> controllerRequestPaths = new ArrayList<>();
                List<RequestMethod> controllerRequestMethods=new ArrayList<>();
                if (controllerRequestMapping != null) {
                    String[] paths=controllerRequestMapping.path();
                    Collections.addAll(controllerRequestPaths, paths);
                    RequestMethod[] requestMethods=controllerRequestMapping.method();
                    Collections.addAll(controllerRequestMethods, requestMethods);
                }


                Method[] methods = controllerClass.getMethods();

                for (Method method : methods) {

                    if (method.isAnnotationPresent(RequestMapping.class)) {


                        //TODO: ready to handle the problems.
                        HandlerMethod handlerMethod = new HandlerMethod(controllerClass.newInstance(), method);

                        Set<String> urlSet = new HashSet<>();


                        //requestMappingInfoList.add(new RequestMappingInfo(handlerMethod, ))

                    }
                }


            } catch (Exception e) {
                throw new ZicException(e.getMessage());
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
}
