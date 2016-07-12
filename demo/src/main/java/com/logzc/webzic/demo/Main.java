package com.logzc.webzic.demo;

import com.logzc.webzic.annotation.RequestMapping;
import com.logzc.webzic.annotation.RestController;
import com.logzc.webzic.demo.controller.UserController;
import com.logzc.webzic.util.PropertyUtil;

/**
 * Created by lishuang on 2016/7/7.
 */
public class Main {

    public static void main(String[] args){

        UserController userController=new UserController();

        Class<?> clazz=userController.getClass();

        if(clazz.isAnnotationPresent(RestController.class)){
            Object obj = clazz.getAnnotation(RestController.class);
            System.out.println(obj);
        }else{
            System.out.println("not shown.");
        }

        if(clazz.isAnnotationPresent(RequestMapping.class)){
            RequestMapping obj = clazz.getAnnotation(RequestMapping.class);
            String[] paths=obj.path();
            for (String path:paths){
                System.out.println(path);
            }
            System.out.println(obj);
        }else{
            System.out.println("not shown.");
        }

    }
}
