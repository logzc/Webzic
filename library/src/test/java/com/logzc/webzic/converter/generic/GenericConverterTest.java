package com.logzc.webzic.converter.generic;

import com.logzc.webzic.converter.ConvertiblePair;
import org.junit.Test;

import java.util.Set;

/**
 * Created by lishuang on 2016/9/5.
 */
public class GenericConverterTest {

    @Test
    public void testGeneric(){


        StringToCollectionConverter converter=new StringToCollectionConverter();

        Set<ConvertiblePair> set =  converter.getConvertibleTypes();

        System.out.println(set.size());

    }

    @Test
    public void testSplit(){
        String a="lishuang,zhangsan";

        String[] as=a.split(",");

        System.out.println(as);
    }
}
