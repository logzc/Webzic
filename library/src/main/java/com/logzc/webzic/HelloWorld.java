package com.logzc.webzic;

import com.logzc.webzic.dynamic.ClassPaths;

import java.net.URL;
import java.util.Collection;
import java.util.Enumeration;

/**
 * Created by lishuang on 2016/7/7.
 */
public class HelloWorld {
    public static void main(String[] args) throws Exception{

        Collection<URL> urls=ClassPaths.forPackage("com");

        for (URL url:urls){
            System.out.println(url);
        }
//        Enumeration<URL> urls = ClassPaths.contextClassLoader().getResources("com");
//
//        while (urls.hasMoreElements()) {
//            URL url = urls.nextElement();
//
//            System.out.println(url);
//
//
//
//        }
    }
}
