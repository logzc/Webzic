package com.logzc.webzic.orm.stmt.crud;

import com.logzc.webzic.orm.db.DbType;
import com.logzc.webzic.orm.field.ColumnType;
import com.logzc.webzic.orm.table.TableInfo;

import java.sql.SQLException;

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
    public static <T,ID> DeleteStatement<T,ID> build(DbType dbType,TableInfo<T,ID> tableInfo) throws SQLException{
        ColumnType idColumnType=tableInfo.getIdColumnType();

        if(idColumnType==null){
            throw new SQLException("Cannot delete from "+tableInfo.getTableClass()+" because it has no primary key.");
        }

        String sql = "DELETE FROM " +
                '`' + tableInfo.getTableName() + '`' + ' ' +
                "WHERE " +
                '`' + idColumnType.getName() + '`' +
                " = ?";

        return new DeleteStatement<T, ID>(tableInfo, sql,new ColumnType[]{idColumnType});
    }

}
