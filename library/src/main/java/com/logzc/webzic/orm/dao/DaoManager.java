package com.logzc.webzic.orm.dao;

import com.logzc.webzic.orm.db.DbType;
import com.logzc.webzic.orm.support.ConnectionSource;
import com.logzc.webzic.orm.table.Table;

import javax.xml.transform.Source;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by lishuang on 2016/8/23.
 */
public class DaoManager {


    private static Map<SourceClassPair,Dao<?,?>> pairMap=new HashMap<>();



    @SuppressWarnings("unchecked")
    public synchronized static <D extends Dao<T,?>,T> D createDao(ConnectionSource connectionSource, Class<T> clazz) throws SQLException {
        if(connectionSource==null){
            throw new IllegalArgumentException("ConnectionSource cannot be null.");
        }

        SourceClassPair pair=new SourceClassPair(connectionSource,clazz);
        Dao<?, ?> dao = pairMap.get(pair);

        if(dao!=null){
            return (D) dao;
        }


        Table table=clazz.getAnnotation(Table.class);
        if(table!=null){

            DbType dbType=connectionSource.getDbType();
            dao=BaseDao.createDao(connectionSource,clazz);

        }else{
            throw new SQLException("@Table must exist.");

        }
        pairMap.put(new SourceClassPair(connectionSource,clazz),dao);

        return (D) dao;

    }




    //pair of ConnectionSrouce and Class.
    private static class SourceClassPair{

        ConnectionSource connectionSource;
        Class<?> clazz;
        public SourceClassPair(ConnectionSource connectionSource,Class<?> clazz){
            this.connectionSource=connectionSource;
            this.clazz=clazz;
        }

        @Override
        public int hashCode(){
            final int prime=31;

            int result=prime+clazz.hashCode();

            result=prime*result+connectionSource.hashCode();

            return result;
        }

        @Override
        public boolean equals(Object object) {
            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            SourceClassPair pair = (SourceClassPair) object;

            return clazz.equals(pair.clazz) && connectionSource.equals(pair.connectionSource);
        }
    }

}
