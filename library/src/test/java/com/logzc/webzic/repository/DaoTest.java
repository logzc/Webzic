package com.logzc.webzic.repository;

import com.logzc.webzic.annotation.Autowired;
import com.logzc.webzic.annotation.Component;
import com.logzc.webzic.junit4.WebzicJUnit4ClassRunner;
import com.logzc.webzic.repository.dao.UserDaoTest;
import com.logzc.webzic.repository.dao.UserTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by lishuang on 2016/10/18.
 */
@Component
@RunWith(WebzicJUnit4ClassRunner.class)
public class DaoTest {

    @Autowired
    UserDaoTest userDaoTest;


    @Test
    public void testUserDao() throws SQLException {

        List<UserTest> userTestList = userDaoTest.queryAll();


        for (UserTest u : userTestList) {
            System.out.println(u.username);
        }

    }
}
