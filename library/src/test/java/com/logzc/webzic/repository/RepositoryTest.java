package com.logzc.webzic.repository;

import com.logzc.webzic.junit4.WebzicJUnit4ClassRunner;
import org.junit.*;
import org.junit.runner.RunWith;

/**
 * Created by lishuang on 2016/10/17.
 */

@RunWith(WebzicJUnit4ClassRunner.class)
public class RepositoryTest {

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Inner beforeClass");
    }

    @Before
    public void before() {
        System.out.println("Inner before");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("Inner afterClass");
    }

    @After
    public void after() {
        System.out.println("Inner after");
    }

    @Test
    public void test1(){
        System.out.println("Inner test1");
    }
    @Test
    public void test2(){
        System.out.println("Inner test2");
    }

}
