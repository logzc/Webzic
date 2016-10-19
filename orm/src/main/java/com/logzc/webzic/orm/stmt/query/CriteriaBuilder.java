package com.logzc.webzic.orm.stmt.query;

import com.logzc.webzic.orm.field.ColumnType;
import com.logzc.webzic.orm.stmt.function.Express;
import com.logzc.webzic.orm.stmt.query.predicate.ComparisonPredicate;
import com.logzc.webzic.orm.stmt.query.predicate.CompoundPredicate;

/**
 * Created by lishuang on 2016/8/26.
 */
public class CriteriaBuilder {

    public Predicate eq(ColumnType columnType, Number i) {

        return new ComparisonPredicate(this, ComparisonOperator.EQ, columnType, i);
    }
    public Predicate eq(ColumnType columnType, String i) {

        return new ComparisonPredicate(this, ComparisonOperator.EQ, columnType, i);
    }


    public Predicate neq(ColumnType columnType, Number i) {

        return new ComparisonPredicate(this, ComparisonOperator.NEQ, columnType, i);
    }

    public Predicate neq(ColumnType columnType, String i) {

        return new ComparisonPredicate(this, ComparisonOperator.NEQ, columnType, i);
    }


    public Predicate gt(ColumnType columnType, Number i) {

        return new ComparisonPredicate(this, ComparisonOperator.GT, columnType, i);
    }
    public Predicate gt(ColumnType columnType, String i) {

        return new ComparisonPredicate(this, ComparisonOperator.GT, columnType, i);
    }


    public Predicate gt(ColumnType columnType, Express i) {

        return new ComparisonPredicate(this, ComparisonOperator.GT, columnType, i);
    }

    public Predicate gte(ColumnType columnType, Number i) {

        return new ComparisonPredicate(this, ComparisonOperator.GTE, columnType, i);
    }
    public Predicate gte(ColumnType columnType, String i) {

        return new ComparisonPredicate(this, ComparisonOperator.GTE, columnType, i);
    }


    public Predicate lt(ColumnType columnType, Number i) {

        return new ComparisonPredicate(this, ComparisonOperator.LT, columnType, i);
    }
    public Predicate lt(ColumnType columnType, String i) {

        return new ComparisonPredicate(this, ComparisonOperator.LT, columnType, i);
    }


    public Predicate lt(ColumnType columnType, Express i) {

        return new ComparisonPredicate(this, ComparisonOperator.LT, columnType, i);
    }


    public Predicate lte(ColumnType columnType, Number i) {

        return new ComparisonPredicate(this, ComparisonOperator.LTE, columnType, i);
    }
    public Predicate lte(ColumnType columnType, String i) {

        return new ComparisonPredicate(this, ComparisonOperator.LTE, columnType, i);
    }



    public Predicate and(Predicate predicate0, Predicate predicate1) {
        return new CompoundPredicate(this, BooleanOperator.AND, predicate0, predicate1);
    }


    public Predicate or(Predicate predicate0, Predicate predicate1) {
        return new CompoundPredicate(this, BooleanOperator.OR, predicate0, predicate1);
    }

}
