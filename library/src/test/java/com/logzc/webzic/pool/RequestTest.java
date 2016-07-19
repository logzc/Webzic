package com.logzc.webzic.pool;

import com.logzc.webzic.annotation.RequestMethod;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lishuang on 2016/7/19.
 */
public class RequestTest {

    @Test
    public void testRequest(){
        ZicRequest zicRequest=new ZicRequest("/hello", RequestMethod.DELETE);
        ZicRequest zicRequest1=new ZicRequest("/hello", RequestMethod.DELETE,RequestMethod.GET);

        Assert.assertTrue(zicRequest.equals(zicRequest1));
        Assert.assertTrue(zicRequest.hashCode()==zicRequest1.hashCode());



    }
}
