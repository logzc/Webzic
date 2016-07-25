package com.logzc.webzic.reflection.type;

/**
 * Created by lishuang on 2016/7/25.
 */
public class TypeTestBean {


    private TypeTestBean(String good) {
        System.out.println(good);
    }

    public TypeTestBean(int a) {

        System.out.println(a);
    }


    public <T> void hello(String a, T num,int a1,boolean b,char c,Integer d, @TypeAnnotation boolean e) {
        System.out.println(a);
    }
}
