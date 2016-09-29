package com.logzc.webzic.reflection.method;

import com.logzc.webzic.annotation.RequestParam;

/**
 * Created by lishuang on 2016/7/27.
 */
public class MethodTestBean0 {


    public MethodTestBean0(String myName) {

    }

    private MethodTestBean0() {

    }
    public void number(long first,double second,float third,char forth,int fifth,boolean sixth,byte seventh,short eighth,String end){
        System.out.println("number hi.");
    }

    public void hello(String name, int second) {
        System.out.println("Hello, " + name+" "+second);
    }

    public void world(@RequestParam(name = "age", required = false, defaultValue = "12") @MethodAnno Integer age, Boolean second) {
        System.out.println("age," + age + " b," + second);
    }

    public <S> void add(S a, S b) {
        System.out.println(a + "," + b);
    }

    public Integer minus(Integer a, Integer b) {

        return a - b;
    }
    public Integer realminus(Integer num1, Integer num2) {

        return num1 -num2;
    }


    private String myPrivate(Boolean adult) {
        System.out.println("this is my private");
        return null;
    }
    private String myPrivate(int adult) {
        System.out.println("this is my private");
        return null;
    }

    public static void staticMethod(int first,double second,float third,char forth,long fifth,boolean sixth,byte seventh,short eighth,String end){

    }
    private static void staticMethod1(){

    }


}
