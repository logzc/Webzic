package com.logzc.webzic.repository.dao;

import com.logzc.webzic.annotation.Repository;
import com.logzc.webzic.orm.dao.Dao;

import java.util.List;

/**
 * Created by lishuang on 2016/10/18.
 */
@Repository
public interface UserDaoTest extends Dao<UserTest, Long> {

    List<UserTest> queryByGender(int gender);

    List<UserTest> queryByUsernameAndGender(String username,int gender);

    List<UserTest> queryByUsernameOrGender(String username,int gender);


    List<UserTest> queryByNothing(String nothing);

}
