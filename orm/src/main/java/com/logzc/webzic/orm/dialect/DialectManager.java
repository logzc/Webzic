package com.logzc.webzic.orm.dialect;

import com.logzc.webzic.orm.dialect.Dialect;
import com.logzc.webzic.orm.dialect.mysql.MysqlDialect;
import com.logzc.webzic.orm.dialect.phoenix.PhoenixDialect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lishuang on 2016/8/23.
 */
public class DialectManager {

    private static List<Dialect> dialects =new ArrayList<>();
    static {
        dialects.add(new MysqlDialect());
        dialects.add(new PhoenixDialect());
    }


    public static Dialect forUrl(String url) {

        for (Dialect dialect : dialects){
            if(dialect.isUrlMatch(url)){
                return dialect;
            }
        }

        return null;
    }
}
