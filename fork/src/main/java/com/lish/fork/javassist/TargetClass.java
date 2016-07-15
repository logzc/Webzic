package com.lish.fork.javassist;

/**
 * This is the target file for assist.
 * Created by lishuang on 2016/7/15.
 */
public class TargetClass {
    private String name="default";
    public TargetClass(){
        name="logzc";
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public void hello(){
        System.out.println("Hello,"+name);
    }
}
