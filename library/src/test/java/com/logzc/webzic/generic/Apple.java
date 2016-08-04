package com.logzc.webzic.generic;

/**
 * Created by lishuang on 2016/8/4.
 */
public class Apple<T> {

    private T info;
    public Apple(T info){
        this.info=info;
    }

    public T getInfo(){
        return this.info;
    }
}
