package com.logzc.webzic.orm.stmt.query;

/**
 * combine many conditions.
 * Created by lishuang on 2016/8/25.
 */
public class Criteria {


    private Condition leftCondition;

    private Criteria leftCriteria;

    private Linker linker;

    private Criteria rightCriteria;

    private Condition rightCondition;

    private Criteria(Condition leftCondition, Criteria leftCriteria, Linker linker, Criteria rightCriteria, Condition rightCondition) {
        this.leftCondition = leftCondition;
        this.leftCriteria = leftCriteria;
        this.linker = linker;
        this.rightCriteria = rightCriteria;
        this.rightCondition = rightCondition;

    }

    public Criteria(Criteria leftCriteria, Linker linker, Criteria rightCriteria) {
        this(null, leftCriteria, linker, rightCriteria, null);
    }

    public Criteria(Condition leftCondition, Linker linker, Condition rightCondition) {
        this(leftCondition, null, linker, null, rightCondition);
    }

    public Criteria(Condition leftCondition) {
        this(leftCondition, null, null, null, null);
    }

    public Criteria(Condition leftCondition, Linker linker, Criteria rightCriteria) {
        this(leftCondition, null, linker, rightCriteria, null);
    }

    public Criteria(Criteria leftCriteria, Linker linker, Condition rightCondition) {
        this(null, leftCriteria, linker, null, rightCondition);
    }


    public String getLinkerVal(){
        switch (linker){
            case AND:
                return "AND";
            case OR:
                return "OR";
            default:
                return null;
        }
    }

    //get the where clause from criteria.
    //TODO: a lot of work here.
    public String getWhereClause(){

        if(leftCondition!=null&&rightCondition!=null){
            return "WHERE `"+leftCondition.getName()+"` "+leftCondition.getOperatorVal()+" ? "+getLinkerVal()+" ``=?";
        }
        return null;
    }


}
