package com.logzc.webzic.orm.stmt.query;

/**
 * Created by lishuang on 2016/8/25.
 */

public enum ComparisonOperator {
    EQ("="),
    NEQ("<>"),
    GT(">"),
    GTE(">="),
    LT("<"),
    LTE("<=");

    private String value;

    ComparisonOperator(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
