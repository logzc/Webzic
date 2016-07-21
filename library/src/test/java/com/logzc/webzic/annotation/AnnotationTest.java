package com.logzc.webzic.annotation;

import org.junit.Test;

/**
 * Created by lishuang on 2016/7/21.
 */
public class AnnotationTest {

    @Test
    public void attributeTest(){

        Class<?> class0=TestBean0.class;
        Class<?> class1=TestBean1.class;
        Class<?> class2=TestBean2.class;
        Class<?> class3=TestBean3.class;

        RequestMapping requestMapping0 = class0.getAnnotation(RequestMapping.class);

        if (requestMapping0!=null){
            String[] paths=requestMapping0.path();
            System.out.println("0");
        }

        RequestMapping requestMapping1 = class1.getAnnotation(RequestMapping.class);

        if (requestMapping1!=null){
            String[] paths=requestMapping1.path();
            System.out.println("1");
        }

        RequestMapping requestMapping2 = class2.getAnnotation(RequestMapping.class);

        if (requestMapping2!=null){
            String[] paths=requestMapping2.path();
            System.out.println("2");
        }

        RequestMapping requestMapping3 = class3.getAnnotation(RequestMapping.class);

        if (requestMapping3!=null){
            String[] paths=requestMapping3.path();
            System.out.println("3");
        }

    }
}
