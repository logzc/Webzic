package com.logzc.webzic.orm.db;


import com.logzc.webzic.orm.field.ColumnType;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.*;

/**
 * Wrap ResultSet. 0 base rather than 1 base in ResultSet.
 * Created by lishuang on 2016/8/24.
 */
public class DbResults implements Closeable {

    private final PreparedStatement preparedStatement;
    private final ResultSet resultSet;
    private final ResultSetMetaData metaData;
    private boolean first = true;

    public DbResults(PreparedStatement preparedStatement, ResultSet resultSet, ResultSetMetaData metaData) throws SQLException {
        this.preparedStatement = preparedStatement;
        this.resultSet = resultSet;
        this.metaData = metaData;
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

    public Object getVal(ColumnType columnType) throws SQLException {


        //TODO: finish other types.
        if (columnType.getType() == String.class) {
            return resultSet.getString(columnType.getName());
        } else if (columnType.getType() == int.class || columnType.getType() == Integer.class) {
            return resultSet.getInt(columnType.getName());
        } else if (columnType.getType() == long.class || columnType.getType() == Long.class) {
            return resultSet.getLong(columnType.getName());
        }else{
            throw new SQLException("not support types.");
        }

    }
}
