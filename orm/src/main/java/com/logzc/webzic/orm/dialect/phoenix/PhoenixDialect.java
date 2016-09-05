package com.logzc.webzic.orm.dialect.phoenix;

import com.logzc.webzic.orm.dialect.Dialect;
import com.logzc.webzic.orm.util.OrmUtils;

import java.sql.Driver;
import java.sql.SQLException;
import java.sql.Types;
import java.text.MessageFormat;
import java.util.Date;

/**
 * Created by lishuang on 2016/8/23.
 */
public class PhoenixDialect implements Dialect {

    Driver driver;

    @Override
    public boolean isUrlMatch(String url) {
        return url != null && url.startsWith("jdbc:phoenix:");

    }

    @Override
    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String getSqlColumn(String columnName) {
        return columnName;
    }

    @Override
    public String buildSqlInsert(String tableName, String[] columnNames) {
        StringBuilder sb = new StringBuilder("UPSERT INTO " + tableName + " (");

        for (int i = 0; i < columnNames.length; i++) {
            String columnName = columnNames[i];
            if (i != 0) {
                sb.append(',');
            }
            sb.append(" ").append(columnName).append(" ");
        }
        sb.append(") VALUES (");

        for (int i = 0; i < columnNames.length; i++) {
            if (i != 0) {
                sb.append(',');
            }
            sb.append("?");
        }
        sb.append(")");

        return sb.toString();
    }

    @Override
    public String buildSqlUpdate(String tableName, String[] columnNames, String idColumnName) {
        StringBuilder sb = new StringBuilder("UPDATE " + tableName + " SET ");


        for (int i = 0; i < columnNames.length; i++) {
            String columnName = columnNames[i];
            if (i != 0) {
                sb.append(',');
            }
            sb.append(" ").append(columnName).append(" = ? ");
        }


        sb.append("WHERE ").append(idColumnName).append(" = ? ;");

        return sb.toString();
    }

    @Override
    public String getSqlSelectFrom() {
        return "SELECT * FROM {0} ";
    }

    @Override
    public String getSqlSelectFromOrderBy() {
        return "SELECT * FROM {0} ORDER BY {1}";
    }


    @Override
    public String getSqlSelectFromWhere() {
        return "SELECT * FROM {0} WHERE {1} ";
    }

    @Override
    public String getSqlSelectFromWhereOrderBy() {
        return "SELECT * FROM {0} WHERE {1} ORDER BY {2}";
    }


    @Override
    public String buildSqlDelete(String tableName, String idColumnName) {

        String sql = "DELETE FROM {0} WHERE {1} = ? ";
        sql = MessageFormat.format(sql, tableName, idColumnName);

        return sql;
    }

    @Override
    public Object getSqlObject(Object arg) {

        if(arg==null){
            return null;
        }

        //special treat of the Date.class.
        if (Date.class == arg.getClass()) {
            Date date = (Date) arg;
            return OrmUtils.dateFormat(date, "yyyy-MM-dd HH:mm:ss");
        }

        return arg;
    }

    @Override
    public int getSqlType(Object arg) throws SQLException {

        if (arg == null) {
            return Types.NULL;
        }
        //special treat of the Date.class.
        if (arg.getClass() == Date.class) {
            return Types.VARCHAR;
        }

        return OrmUtils.getSqlType(arg.getClass());
    }
}
