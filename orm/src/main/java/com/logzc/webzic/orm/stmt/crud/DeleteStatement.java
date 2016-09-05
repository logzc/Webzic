package com.logzc.webzic.orm.stmt.crud;

import com.logzc.webzic.orm.field.ColumnType;
import com.logzc.webzic.orm.support.ConnectionSource;
import com.logzc.webzic.orm.table.TableInfo;

import java.sql.SQLException;

/**
 * Use this class to delete one row.
 * <p>
 * Created by lishuang on 2016/8/23.
 */
public class DeleteStatement<T, ID> extends BaseStatement<T, ID> {

    protected DeleteStatement(ConnectionSource connectionSource, TableInfo<T, ID> tableInfo) {
        super(connectionSource, tableInfo);


        this.statement = this.dialect.buildSqlDelete(tableInfo.getTableName(), tableInfo.getIdColumnType().getName());


    }

    /**
     * DELETE FROM account WHERE id = ?
     */
    public static <T, ID> DeleteStatement<T, ID> build(ConnectionSource connectionSource, TableInfo<T, ID> tableInfo) throws SQLException {
        ColumnType idColumnType = tableInfo.getIdColumnType();

        if (idColumnType == null) {
            //throw new SQLException("Cannot delete from "+tableInfo.getTableClass()+" because it has no primary key.");
            return null;
        }


        return new DeleteStatement<>(connectionSource, tableInfo);
    }

}
