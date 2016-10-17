package com.logzc.webzic.junit4;

import com.logzc.webzic.annotation.Autowired;
import com.logzc.webzic.annotation.Component;
import com.logzc.webzic.bean.AppContext;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by lishuang on 2016/10/17.
 */
@Component
@RunWith(WebzicJUnit4ClassRunner.class)
public class ComponentTest {

    @Autowired
    Junit4Component junit4Component;

    @Test
    public void test(){

        ComponentTest componentTest= AppContext.getBean(ComponentTest.class);
        componentTest.junit4Component.haha();

        this.junit4Component.haha();

        System.out.println("-------------------");
    }



}
