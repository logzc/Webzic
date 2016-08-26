package com.logzc.webzic.orm.stmt.query.predicate;

import com.logzc.webzic.orm.field.ColumnType;
import com.logzc.webzic.orm.stmt.query.ComparisonOperator;
import com.logzc.webzic.orm.stmt.query.CriteriaBuilder;
import com.logzc.webzic.orm.stmt.query.Predicate;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lishuang on 2016/8/26.
 */
public class ComparisonPredicate implements Predicate {

    CriteriaBuilder criteriaBuilder;
    ComparisonOperator comparisonOperator;
    ColumnType columnType;
    Number number;

    String statement;

    public ComparisonPredicate(CriteriaBuilder criteriaBuilder, ComparisonOperator comparisonOperator, ColumnType columnType, Number number) {
        this.criteriaBuilder = criteriaBuilder;
        this.comparisonOperator = comparisonOperator;
        this.columnType = columnType;
        this.number = number;
    }

    @Override
    public String getStatement() {

        if (statement == null) {
            statement = " `{0}` {1} ? ";
            statement = MessageFormat.format(statement, columnType.getName(), comparisonOperator.getValue());
        }
        return statement;
    }

    @Override
    public List<Object> getArgs() {

        List<Object> argsList = new ArrayList<>();
        argsList.add(number);
        return argsList;
    }
}
