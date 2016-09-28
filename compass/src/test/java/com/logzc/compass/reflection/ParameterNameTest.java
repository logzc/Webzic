package com.logzc.compass.reflection;

import com.logzc.webzic.annotation.RestController;
import com.logzc.webzic.compass.controller.SiteController;
import com.logzc.webzic.reflection.ClassPaths;
import com.logzc.webzic.reflection.Reflections;
import com.logzc.webzic.reflection.scanner.Scanner;
import com.logzc.webzic.reflection.scanner.TypeAnnotationScanner;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Collection;
import java.util.List;

/**
 * Created by lishuang on 2016/7/27.
 */
public class ParameterNameTest {

    @Test
    public void testMethod() {
        System.out.println("hello world");

    }

    @Test
    public void testClasspath(){

        Collection<URL> urls= ClassPaths.forPackage("com.logzc.webzic.compass");

        for (URL url:urls){
            System.out.println(urls);
        }

        Scanner scanner=new TypeAnnotationScanner(RestController.class);

        Reflections.scan(scanner);

        List<String> names = scanner.getClassNames();
        for (String name:names){
            System.out.println(name);
        }
    }
}
