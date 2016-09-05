package com.logzc.webzic.orm.stmt.query.predicate;

import com.logzc.webzic.orm.dialect.Dialect;
import com.logzc.webzic.orm.stmt.query.Predicate;

import java.util.List;

/**
 * Created by lishuang on 2016/8/26.
 */
public class BetweenPredicate implements Predicate {

    @Override
    public String getStatement(Dialect dialect) {
        return null;
    }

    @Override
    public List<Object> getArgs() {
        return null;
    }
}
