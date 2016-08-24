package com.logzc.webzic.orm.stmt.crud;

import com.logzc.webzic.orm.db.DbType;
import com.logzc.webzic.orm.field.ColumnType;
import com.logzc.webzic.orm.table.TableInfo;

import java.sql.SQLException;
import java.text.MessageFormat;

/**
 * Use this class to delete one row.
 *
 * Created by lishuang on 2016/8/23.
 */
public class DeleteStatement<T,ID> extends BaseStatement<T,ID> {

    protected DeleteStatement(TableInfo<T, ID> tableInfo, String statement, ColumnType[] argColumnTypes) {
        super(tableInfo, statement, argColumnTypes);
    }

    /**
     * DELETE FROM account WHERE id = ?
     *
     */
    public static <T,ID> DeleteStatement<T,ID> build(TableInfo<T,ID> tableInfo) throws SQLException{
        ColumnType idColumnType=tableInfo.getIdColumnType();

        if(idColumnType==null){
            throw new SQLException("Cannot delete from "+tableInfo.getTableClass()+" because it has no primary key.");
        }

        String sql = "DELETE FROM `{0}` WHERE `{1}` = ? ;";
        sql = MessageFormat.format(sql, tableInfo.getTableName(), tableInfo.getIdColumnType().getName());

        return new DeleteStatement<>(tableInfo, sql,new ColumnType[]{idColumnType});
    }

}
