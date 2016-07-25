package com.logzc.webzic;

import com.logzc.webzic.web.core.RequestMethod;

/**
 * Created by lishuang on 2016/7/7.
 */
public class HelloWorld {

    public static void main(String[] args) throws Exception{

        System.out.println("-----------------------");

//        Collection<URL> urls1=ClassPaths.forJavaClassPath();
//
//        for (URL url:urls1){
//            System.out.println(url);
//        }

        System.out.println(RequestMethod.valueOf("GET"));




    }
}
