package com.logzc.webzic.orm.stmt.query.predicate;

import com.logzc.webzic.orm.stmt.query.BooleanOperator;
import com.logzc.webzic.orm.stmt.query.CriteriaBuilder;
import com.logzc.webzic.orm.stmt.query.Predicate;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lishuang on 2016/8/26.
 */
public class CompoundPredicate implements Predicate {
    CriteriaBuilder criteriaBuilder;
    BooleanOperator operator;
    Predicate leftPredicate;
    Predicate rightPredicate;
    String statement;

    public CompoundPredicate(CriteriaBuilder criteriaBuilder, BooleanOperator operator, Predicate leftPredicate, Predicate rightPredicate) {

        this.criteriaBuilder = criteriaBuilder;
        this.operator = operator;
        this.leftPredicate = leftPredicate;
        this.rightPredicate = rightPredicate;
    }

    @Override
    public String getStatement() {

        if (statement == null) {
            statement = " ( {0} {1} {2} ) ";
            statement = MessageFormat.format(statement,leftPredicate.getStatement(), operator.name(), rightPredicate.getStatement());
        }
        return statement;
    }

    @Override
    public List<Object> getArgs() {

        List<Object> args = new ArrayList<>();

        args.addAll(leftPredicate.getArgs());
        args.addAll(rightPredicate.getArgs());

        return args;
    }
}
