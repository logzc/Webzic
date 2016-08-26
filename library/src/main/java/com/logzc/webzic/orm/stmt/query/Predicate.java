package com.logzc.webzic.orm.stmt.query;

import java.util.List;

/**
 * Created by lishuang on 2016/8/26.
 */
public interface Predicate {

    String getStatement();

    List<Object> getArgs();
}
