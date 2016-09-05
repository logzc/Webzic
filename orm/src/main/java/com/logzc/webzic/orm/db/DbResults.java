package com.logzc.webzic.orm.db;


import com.logzc.webzic.orm.field.ColumnType;
import com.logzc.webzic.orm.table.TableInfo;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Wrap ResultSet. 0 base rather than 1 base in ResultSet.
 * Created by lishuang on 2016/8/24.
 */
public class DbResults implements Closeable {

    private final PreparedStatement preparedStatement;
    private final ResultSet resultSet;
    private final ResultSetMetaData metaData;
    private boolean first = true;

    public DbResults(PreparedStatement preparedStatement, ResultSet resultSet) throws SQLException {
        this.preparedStatement = preparedStatement;
        this.resultSet = resultSet;
        this.metaData = resultSet.getMetaData();
    }


    public int getColumnCount() throws SQLException {

        return metaData.getColumnCount();

    }

    public String[] getColumnNames() throws SQLException {

        int columnNumber = metaData.getColumnCount();
        String[] columnNames = new String[columnNumber];
        for (int i = 0; i < columnNumber; i++) {
            columnNames[i] = metaData.getColumnLabel(i + 1);
        }
        return columnNames;

    }

    public boolean first() throws SQLException {

        if (first) {
            first = false;
            return next();
        } else {
            return resultSet.first();
        }

    }

    public boolean next() throws SQLException {

        //not close here.
        if (resultSet.next()) {
            return true;
        } else if (!preparedStatement.getMoreResults()) {
            return false;
        } else {
            return resultSet.next();
        }

    }

    public boolean last() throws SQLException {

        return resultSet.last();

    }

    public boolean previous() throws SQLException {

        return resultSet.previous();

    }

    public boolean moveRelative(int offset) throws SQLException {

        return resultSet.relative(offset);

    }

    public boolean moveAbsolute(int position) throws SQLException {

        return resultSet.absolute(position);

    }

    public int findColumn(String columnName) throws SQLException {

        return resultSet.findColumn(columnName) - 1;

    }

    public InputStream getBlobStream(int columnIndex) throws SQLException {

        Blob blob = resultSet.getBlob(columnIndex + 1);
        if (blob == null) {
            return null;
        } else {
            return blob.getBinaryStream();
        }

    }

    public boolean getBoolean(int columnIndex) throws SQLException {
        return resultSet.getBoolean(columnIndex + 1);


    }

    public char getChar(int columnIndex) throws SQLException {

        String string = resultSet.getString(columnIndex + 1);

        if (string == null || string.length() == 0) {
            return 0;
        } else if (string.length() == 1) {
            return string.charAt(0);
        } else {
            throw new SQLException("More than 1 char stored in db.");
        }

    }

    public byte getByte(int columnIndex) throws SQLException {

        return resultSet.getByte(columnIndex + 1);

    }

    public byte[] getBytes(int columnIndex) throws SQLException {

        return resultSet.getBytes(columnIndex + 1);

    }

    public double getDouble(int columnIndex) throws SQLException {

        return resultSet.getDouble(columnIndex + 1);

    }

    public float getFloat(int columnIndex) throws SQLException {

        return resultSet.getFloat(columnIndex + 1);

    }

    public int getInt(int columnIndex) throws SQLException {

        return resultSet.getInt(columnIndex + 1);

    }

    public long getLong(int columnIndex) throws SQLException {

        return resultSet.getLong(columnIndex + 1);

    }

    public short getShort(int columnIndex) throws SQLException {

        return resultSet.getShort(columnIndex + 1);

    }

    public String getString(int columnIndex) throws SQLException {

        return resultSet.getString(columnIndex + 1);

    }

    public Timestamp getTimestamp(int columnIndex) throws SQLException {

        return resultSet.getTimestamp(columnIndex + 1);

    }

    public BigDecimal getBigDecimal(int columnIndex) throws SQLException {

        return resultSet.getBigDecimal(columnIndex + 1);

    }


    public Object getObject(int columnIndex) throws SQLException {

        return resultSet.getObject(columnIndex + 1);

    }

    public boolean wasNull() throws SQLException {

        return resultSet.wasNull();

    }


    @Override
    public void close() throws IOException {
        try {
            resultSet.close();
        } catch (SQLException e) {
            throw new IOException("Could not close result set.");
        }
    }

    public void closeQuietly() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                //ignored.
            }
        }
    }

    public Object getVal(ColumnType columnType) throws SQLException {

        String columnName = columnType.getName();

        Object val = null;


        //check whether the result exists in the result set.
        try {

            resultSet.findColumn(columnName);
        } catch (SQLException e) {
            return null;
        }


        Class<?> type = columnType.getType();
        if (type == int.class || type == Integer.class) {
            val = resultSet.getInt(columnName);
        } else if (type == long.class || type == Long.class) {
            val = resultSet.getLong(columnName);
        } else if (type == float.class || type == Float.class) {
            val = resultSet.getFloat(columnName);
        } else if (type == double.class || type == Double.class) {
            val = resultSet.getDouble(columnName);
        } else if (type == boolean.class || type == Boolean.class) {
            val = resultSet.getBoolean(columnName);
        } else if (type == java.util.Date.class) {
            val = resultSet.getDate(columnName);
        } else if (type == String.class) {
            val = resultSet.getString(columnName);
        } else {
            System.out.println("Not supported types.");
            throw new SQLException("Not supported types.");
        }


        return val;
    }


    //get the first result.
    public <T> T getEntity(TableInfo<T, ?> tableInfo) throws SQLException {

        //this will start the result cursor.
        if (!this.first()) {
            // no results at all
            return null;
        }

        T entity = tableInfo.createEntity();
        for (ColumnType columnType : tableInfo.getColumnTypes()) {


            //if the result set has such column parse it.


            Object val = this.getVal(columnType);

            if(val!=null){
                columnType.assign(entity, val);
            }

        }

        return entity;
    }

    public <T> List<T> getEntityList(TableInfo<T, ?> tableInfo) throws SQLException {

        List<T> entityList = new ArrayList<>();
        while (resultSet.next()) {

            T entity = tableInfo.createEntity();
            for (ColumnType columnType : tableInfo.getColumnTypes()) {

                Object val = this.getVal(columnType);

                if(val!=null){
                    columnType.assign(entity, val);
                }
            }

            entityList.add(entity);
        }

        return entityList;
    }
}
