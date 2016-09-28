package com.logzc.compass.property;

import com.logzc.webzic.util.PropertyUtil;
import org.junit.Test;

/**
 * Created by lishuang on 2016/9/28.
 */
public class PropertyTest {


    @Test
    public void testOverrideProperty(){


        String url = PropertyUtil.getProperty("/config.properties","jdbc.url");


        System.out.println(url);

    }
}
