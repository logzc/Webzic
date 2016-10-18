package com.logzc.webzic.orm.dao;

import com.logzc.webzic.orm.support.ConnectionSource;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.SQLException;

/**
 * Created by lishuang on 2016/10/12.
 */
public class DaoHandler<T, ID> extends BaseDao<T, ID> implements InvocationHandler {


    public DaoHandler(ConnectionSource connectionSource, Class<T> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        try {

            Object result = method.invoke(this, args);

            return result;
        } catch (Exception e) {

            //Try to match queryByXXXAndXXX queryByXXXOrXXX

            return null;

        }



    }
}
