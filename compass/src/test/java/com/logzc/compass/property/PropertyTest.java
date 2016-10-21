package com.logzc.compass.property;

import com.logzc.webzic.reflection.ClassPaths;
import com.logzc.common.util.PropertyUtil;
import com.logzc.webzic.web.controller.WebzicPath;
import org.junit.Assume;
import org.junit.Test;

import java.net.URL;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by lishuang on 2016/9/28.
 */
public class PropertyTest {


    @Test
    public void testOverrideProperty(){


        String url = PropertyUtil.getProperty("/config.properties","jdbc.url");


        System.out.println(url);

    }

    @Test
    public void testCollections(){
        Collection<URL> urls1= ClassPaths.forPackage("com.logzc");
        Collection<URL> urls2= ClassPaths.forPackage("com.logzc.webzic");

        Collection<URL> urls=new HashSet<>();
        urls.addAll(urls2);
        urls.addAll(urls1);

        Assume.assumeTrue(urls.size()==3);

        Assume.assumeTrue(WebzicPath.class.getPackage().getName().equals("com.logzc.webzic.web.controller"));

    }
}
