package com.logzc.webzic.orm.stmt.function;

import com.logzc.webzic.orm.dialect.Dialect;

import java.util.List;

/**
 * sql functions.
 * Created by lishuang on 2016/8/30.
 */
public interface Express {

    String getStatement(Dialect dialect);

    List<Object> getArgs();


}
