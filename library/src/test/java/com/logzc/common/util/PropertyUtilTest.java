package com.logzc.common.util;

import org.junit.Test;

/**
 * Created by lishuang on 2016/9/28.
 */
public class PropertyUtilTest {

    @Test
    public void testGet(){

        String url = PropertyUtil.getProperty("/com.logzc.webzic/logzc/webzic/config.properties","jdbc.url");


        System.out.println(url);
    }
}
