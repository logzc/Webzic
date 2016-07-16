package com.logzc.webzic.demo;

import com.lish.fork.javassist.InvisibleAn;
import com.lish.fork.javassist.VisibleAn;
import com.logzc.webzic.annotation.RequestMapping;
import com.logzc.webzic.annotation.RestController;
import com.logzc.webzic.demo.controller.UserController;
import com.logzc.webzic.util.ClassUtil;
import com.logzc.webzic.util.PropertyUtil;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.SystemUtils;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Created by lishuang on 2016/7/7.
 */
@VisibleAn
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) throws Exception {


        //scan urls that contain 'my.package', include inputs starting with 'my.package', use the default scanners


        Reflections reflections = new Reflections("com.lish");
        //TypeAnnotationsScanner
        Set<Class<?>> singletons =
                reflections.getTypesAnnotatedWith(VisibleAn.class);
        for (Class<?> clazz:singletons ){

            System.err.println(clazz);

        }
        Set<Class<?>> singletons1 =
                reflections.getTypesAnnotatedWith(InvisibleAn.class);
        for (Class<?> clazz:singletons1 ){

            System.err.println(clazz);

        }




    }
}
