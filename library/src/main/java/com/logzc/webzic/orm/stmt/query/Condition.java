package com.logzc.webzic.orm.stmt.query;

/**
 * Created by lishuang on 2016/8/25.
 */
public class Condition {

    private String name;
    private Operator operator;
    private Object value;

    public Condition(String name, Operator operator, Object value) {
        this.name = name;
        this.operator = operator;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public String getOperatorVal() {
        String val = null;
        switch (operator) {
            case EQ:
                val = "=";
                break;
            case NEQ:
                val = "!=";
                break;
            case GT:
                val = ">";
                break;
            case LT:
                val = "<";
                break;
            case LIKE:
                val = "LIKE";
                break;
        }
        return val;
    }
}
