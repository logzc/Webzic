package com.logzc.webzic.orm.dialect.phoenix.function;

import com.logzc.webzic.orm.dialect.Dialect;
import com.logzc.webzic.orm.stmt.function.Function;
import com.logzc.webzic.orm.util.OrmUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lishuang on 2016/8/30.
 */
public class ToDateFunction implements Function {

    private Date date;

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";


    public ToDateFunction(Date date) {
        this.date = date;
    }

    @Override
    public String getStatement(Dialect dialect) {

        return "TO_DATE( ? , '" + DATE_FORMAT + "', 'GMT+1')";

    }


    @Override
    public List<Object> getArgs() {

        List<Object> args = new ArrayList<>();
        args.add(OrmUtils.dateFormat(this.date, DATE_FORMAT));
        return args;

    }

}
