package com.logzc.webzic.collection;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

/**
 * Created by lishuang on 2016/7/29.
 */
public class CollectionTest {

    @Test
    public void testCopy() {


        List list = Collections.nCopies(5, null);

        System.out.println(list);

        List list1 = Collections.nCopies(5, "lish");


        System.out.println(list1);



    }
}
