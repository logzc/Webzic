package com.lish.fork.db;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lishuang on 2016/6/23.
 */
public class DBUtilsTest {

    private static final Logger logger = LoggerFactory.getLogger(DBUtilsTest.class);

    @Before
    public void init() throws Exception {
        logger.debug("Before Test");
    }


    @Test
    public void add() throws SQLException {
        String sql = "insert into users(name,password,email,birthday) values(?,?,?,?)";
        Object[] params = {"李爽", "123", "gacl@sina.com", new Date()};

        logger.debug("Finish Logging.");
    }


}
