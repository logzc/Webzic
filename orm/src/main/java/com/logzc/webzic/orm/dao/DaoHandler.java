package com.logzc.webzic.orm.dao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by lishuang on 2016/10/12.
 */
public class DaoHandler implements InvocationHandler{



    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("DaoHandler:");
        System.out.println("Method->"+method);

        return null;
    }
}
