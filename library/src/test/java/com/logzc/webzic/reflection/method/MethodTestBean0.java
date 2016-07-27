package com.logzc.webzic.reflection.method;

import com.logzc.webzic.annotation.RequestParam;

/**
 * Created by lishuang on 2016/7/27.
 */
public class MethodTestBean0 {

    public void hello(String name, int a) {
        System.out.println("Hello, " + name);
    }

    public void world(@RequestParam(name = "age", required = false, defaultValue = "12") @MethodAnno Integer age, Boolean b) {
        System.out.println("age," + age + " b," + b);
    }

    public <S> void add(S a, S b) {
        System.out.println(a + "," + b);
    }

    public Integer minus(Integer a, Integer b) {

        return a - b;
    }
}
