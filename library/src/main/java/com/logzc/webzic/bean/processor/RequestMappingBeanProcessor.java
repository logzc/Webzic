package com.logzc.webzic.bean.processor;

import com.logzc.webzic.annotation.RequestMapping;
import com.logzc.webzic.bean.AppContext;
import com.logzc.webzic.web.core.HandlerMethod;
import com.logzc.webzic.web.core.HandlerMethodManager;
import com.logzc.webzic.web.core.RequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.*;

/**
 * process @RequestMap annotation for beans.
 * Created by lishuang on 2016/9/28.
 */
public class RequestMappingBeanProcessor implements BeanProcessor {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static Properties properties = null;

    @Override
    public void beforeInit(Object bean, Class<?> clazz) throws Exception {

    }

    /**
     * Read the annotations of the controller class and create the map index.
     */
    @Override
    public void afterInit(Object bean, Class<?> controllerClass) throws Exception {


        HandlerMethodManager handlerMethodManager = AppContext.getHandlerMethodManager();

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

                HandlerMethod handlerMethod = new HandlerMethod(bean, method, finalPaths, finalRequestMethods);



                for (String path : finalPaths) {

                    handlerMethodManager.add(path, handlerMethod);

                }

            }
        }


    }
}
