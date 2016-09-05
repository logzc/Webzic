package com.logzc.webzic.orm.mysql;

import com.logzc.webzic.orm.table.Table;

/**
 * Created by lishuang on 2016/8/29.
 */
public class TempTable {


    @Table
    public static class Temp1{
        public int num;
        public boolean deleted;
    }

}
