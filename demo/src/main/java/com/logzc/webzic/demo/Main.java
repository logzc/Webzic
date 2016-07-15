package com.logzc.webzic.demo;

import com.logzc.webzic.annotation.RequestMapping;
import com.logzc.webzic.annotation.RestController;
import com.logzc.webzic.demo.controller.UserController;
import com.logzc.webzic.util.ClassUtil;
import com.logzc.webzic.util.PropertyUtil;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.SystemUtils;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Created by lishuang on 2016/7/7.
 */
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) throws Exception {


        //scan urls that contain 'my.package', include inputs starting with 'my.package', use the default scanners

        Reflections reflections = new Reflections("");
        //TypeAnnotationsScanner
        Set<Class<?>> singletons =
                reflections.getTypesAnnotatedWith(RestController.class);

        for (Class<?> clazz:singletons ){

            System.err.println(clazz);

        }



        //ClassUtil.getClassSet("com.logzc");


    }
}
