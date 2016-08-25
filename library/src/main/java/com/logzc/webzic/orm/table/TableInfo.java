package com.logzc.webzic.orm.table;

import com.logzc.webzic.orm.dao.BaseDao;
import com.logzc.webzic.orm.field.ColumnType;
import com.logzc.webzic.orm.support.ConnectionSource;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lishuang on 2016/8/23.
 */
public class TableInfo<T, ID> {


    private final BaseDao<T, ID> baseDao;
    private final Class<T> tableClass;
    private final String tableName;
    protected ColumnType[] columnTypes;
    private ColumnType idColumnType;

    public TableInfo(ConnectionSource connectionSource, BaseDao<T, ID> baseDao, Class<T> tableClass) throws SQLException {

        this.baseDao = baseDao;
        this.tableClass = tableClass;
        this.tableName = extractTableName(tableClass);
        this.columnTypes = extractColumnTypes(connectionSource, tableClass, tableName);


        //find the id column.
        for (ColumnType columnType : this.columnTypes) {
            if (columnType.isId()) {
                if (idColumnType == null) {
                    idColumnType = columnType;
                } else {
                    throw new SQLException("multi id for column defined.");
                }
            }
        }


    }

    private ColumnType[] extractColumnTypes(ConnectionSource connectionSource, Class<T> dataClass, String tableName) {

        List<ColumnType> columnTypes = new ArrayList<>();

        //not only self but also super father.
        for (Class<?> classWalk = dataClass; classWalk != null; classWalk = classWalk.getSuperclass()) {

            for (Field field : classWalk.getDeclaredFields()) {

                ColumnType columnType = ColumnType.create(connectionSource, tableName, field, dataClass);

                if (columnType != null) {
                    columnTypes.add(columnType);
                }
            }
        }


        if (columnTypes.isEmpty()) {
            throw new IllegalArgumentException("No columns configured.");
        }

        return columnTypes.toArray(new ColumnType[columnTypes.size()]);
    }


    //get the table name from Class.
    public String extractTableName(Class<T> clazz) {

        Table table = clazz.getAnnotation(Table.class);

        String name = null;

        if (table != null && table.name().length() > 0) {
            name = table.name();
        }

        if (name == null) {
            name = clazz.getSimpleName().toLowerCase();
        }

        return name;

    }


    public Object[] getColumnValues(T entity) throws SQLException {
        int length = this.columnTypes.length;
        Object[] objects = new Object[length];
        for (int i = 0; i < length; i++) {
            ColumnType columnType = this.columnTypes[i];


            objects[i] = columnType.getValue(entity);
        }
        return objects;
    }

    public Class<T> getTableClass() {
        return tableClass;
    }

    public ColumnType getIdColumnType() {
        return idColumnType;
    }

    public String getTableName() {
        return tableName;
    }

    public ColumnType[] getColumnTypes() {
        return columnTypes;
    }

    public T createEntity() throws SQLException {
        try {
            return this.tableClass.newInstance();
        } catch (Exception e) {
            throw new SQLException("Create entity exception.");
        }

    }

    @SuppressWarnings("unchecked")
    public ID getIdValue(T entity) throws SQLException {
        if (idColumnType == null) {
            return null;
        } else {
            return (ID) idColumnType.getValue(entity);
        }
    }
}
