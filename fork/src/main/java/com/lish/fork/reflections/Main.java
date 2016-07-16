package com.lish.fork.reflections;

import com.lish.fork.javassist.InvisibleAn;
import com.lish.fork.javassist.VisibleAn;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;

import java.net.URL;
import java.util.Collection;
import java.util.Set;

/**
 * Created by lishuang on 2016/7/16.
 */
public class Main {
    public static void main(String[] args){

        //scan urls that contain 'my.package', include inputs starting with 'my.package', use the default scanners


        Reflections reflections = new Reflections("com.lish");
        //TypeAnnotationsScanner
        Set<Class<?>> singletons =
                reflections.getTypesAnnotatedWith(InvisibleAn.class);

        for (Class<?> clazz:singletons ){

            System.err.println(clazz);

        }


        Collection<URL> urls = ClasspathHelper.forPackage("");

        System.out.println(urls);




    }
}
