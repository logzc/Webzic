package com.logzc.webzic.compass.dao;

import com.logzc.webzic.annotation.Repository;
import com.logzc.webzic.compass.model.User;
import com.logzc.webzic.orm.dao.Dao;

/**
 * Created by lishuang on 2016/10/11.
 */
@Repository
public interface UserDao extends Dao<User,Long>{
}
