package com.logzc.webzic.orm.dialect.mysql;

import com.logzc.webzic.orm.dialect.Dialect;
import com.logzc.webzic.orm.util.OrmUtils;

import java.sql.Driver;
import java.sql.SQLException;
import java.text.MessageFormat;

/**
 * Created by lishuang on 2016/8/23.
 */
public class MysqlDialect implements Dialect {

    Driver driver;

    @Override
    public boolean isUrlMatch(String url) {
        return url != null && url.startsWith("jdbc:mysql:");
    }

    @Override
    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String getSqlColumn(String columnName) {
        return "`"+columnName+"`";
    }

    @Override
    public String buildSqlInsert(String tableName, String[] columnNames) {
        StringBuilder sb = new StringBuilder("INSERT INTO `" + tableName + "` (");

        boolean first = true;

        for (String columnName : columnNames) {
            if (first) {
                first = false;
            } else {
                sb.append(',');
            }
            sb.append("`").append(columnName).append("`");
        }

        sb.append(") VALUES (");

        first = true;
        for (String ignored : columnNames) {
            if (first) {
                first = false;
            } else {
                sb.append(',');
            }
            sb.append("?");
        }
        sb.append(");");

        return sb.toString();
    }


    @Override
    public String buildSqlUpdate(String tableName, String[] columnNames, String idColumnName) {
        StringBuilder sb = new StringBuilder("UPDATE `" + tableName + "` SET ");


        for (int i = 0; i < columnNames.length; i++) {
            String columnName = columnNames[i];
            if (i != 0) {
                sb.append(',');
            }
            sb.append("`").append(columnName).append("` = ? ");
        }


        sb.append("WHERE `").append(idColumnName).append("` = ? ;");

        return sb.toString();
    }

    @Override
    public String getSqlSelectFrom() {
        return "SELECT * FROM `{0}` ;";
    }

    @Override
    public String getSqlSelectFromOrderBy() {
        return "SELECT * FROM `{0}` ORDER BY {1};";
    }

    @Override
    public String getSqlSelectFromWhere() {
        return "SELECT * FROM `{0}` WHERE {1} ;";
    }

    @Override
    public String getSqlSelectFromWhereOrderBy() {
        return "SELECT * FROM `{0}` WHERE {1} ORDER BY {2};";
    }

    @Override
    public String buildSqlDelete(String tableName, String idColumnName) {

        String sql = "DELETE FROM `{0}` WHERE `{1}` = ? ;";
        sql = MessageFormat.format(sql, tableName, idColumnName);

        return sql;
    }

    @Override
    public Object getSqlObject(Object arg) {
        return arg;
    }

    @Override
    public int getSqlType(Object arg) throws SQLException {
        return OrmUtils.getSqlType(arg.getClass());
    }

}
