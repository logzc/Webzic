package com.logzc.webzic.clazz;

/**
 * Created by lishuang on 2016/7/13.
 */
public class Foo {
    static {
        System.out.println("in the static block.");
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void hello(){
        System.out.println("Hello world");
    }
}
