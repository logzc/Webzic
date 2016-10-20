package com.logzc.webzic.orm.stmt.query.predicate;

import com.logzc.webzic.orm.dialect.Dialect;
import com.logzc.webzic.orm.field.ColumnType;
import com.logzc.webzic.orm.stmt.function.Express;
import com.logzc.webzic.orm.stmt.query.ComparisonOperator;
import com.logzc.webzic.orm.stmt.query.CriteriaBuilder;
import com.logzc.webzic.orm.stmt.query.Predicate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lishuang on 2016/8/26.
 */
public class ComparisonPredicate implements Predicate {

    CriteriaBuilder criteriaBuilder;
    ComparisonOperator comparisonOperator;
    ColumnType columnType;

    Express express;

    Object value;

    String statement;

    public ComparisonPredicate(CriteriaBuilder criteriaBuilder, ComparisonOperator comparisonOperator, ColumnType columnType, Object obj) {
        this.criteriaBuilder = criteriaBuilder;
        this.comparisonOperator = comparisonOperator;
        this.columnType = columnType;

        if (obj != null && (obj instanceof Express)) {
            this.express = (Express) obj;
        } else {
            this.value = obj;
        }

    }

    // eg. `age` < 5
    @Override
    public String getStatement(Dialect dialect) {


        if (statement == null) {
            statement = " " + dialect.getSqlColumn(columnType.getName()) + " " + comparisonOperator.getValue();
            if (this.value != null) {
                statement += " ? ";
            } else if (this.express != null) {
                statement += " " + this.express.getStatement(dialect);
            }

        }
        return statement;
    }

    @Override
    public List<Object> getArgs() {

        List<Object> argsList = new ArrayList<>();
        if (this.value != null) {
            argsList.add(this.value);
        } else if (this.express != null) {
            argsList.addAll(this.express.getArgs());
        }

        return argsList;
    }
}
