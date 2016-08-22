package com.logzc.webzic.orm.db;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lishuang on 2016/8/23.
 */
public class DbTypeUtils {

    private static List<DbType> dbTypes=new ArrayList<>();
    static {
        dbTypes.add(new MysqlDbType());
    }


    public static DbType forUrl(String url) {

        for (DbType dbType:dbTypes){
            if(dbType.isUrlMatch(url)){
                return dbType;
            }
        }

        return null;
    }
}
