package com.logzc.webzic.converter;

/**
 * Created by lishuang on 2016/8/22.
 */
public class SerializableTypeWrapperBean {

    public String address;

    public <T> long getAge(String name, T job) {

        System.out.println(job);
        return System.currentTimeMillis();
    }



}
