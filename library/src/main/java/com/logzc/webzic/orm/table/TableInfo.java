package com.logzc.webzic.orm.table;

import com.logzc.webzic.orm.dao.BaseDao;
import com.logzc.webzic.orm.field.ColumnType;
import com.logzc.webzic.orm.support.ConnectionSource;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lishuang on 2016/8/23.
 */
public class TableInfo<T, ID> {


    private final BaseDao<T, ID> baseDao;
    private final Class<T> dataClass;
    private final String tableName;
    protected ColumnType[] columnTypes;

    public TableInfo(ConnectionSource connectionSource, BaseDao<T, ID> baseDao, Class<T> dataClass) {

        this.baseDao = baseDao;
        this.dataClass = dataClass;
        this.tableName = extractTableName(dataClass);
        this.columnTypes = extractColumnTypes(connectionSource, dataClass, tableName);



    }

    private ColumnType[] extractColumnTypes(ConnectionSource connectionSource, Class<T> dataClass, String tableName) {

        List<ColumnType> columnTypes = new ArrayList<>();

        //not only self but also super father.
        for (Class<?> classWalk = dataClass; classWalk != null; classWalk = classWalk.getSuperclass()) {

            for (Field field : classWalk.getDeclaredFields()) {

                ColumnType columnType = ColumnType.createColumnType(connectionSource, tableName, field, dataClass);

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

}
