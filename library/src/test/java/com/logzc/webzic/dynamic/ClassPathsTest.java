package com.logzc.webzic.dynamic;

import org.junit.Test;

import java.net.URL;
import java.util.Collection;

/**
 * Created by lishuang on 2016/7/18.
 */
public class ClassPathsTest {

    @Test
    public void testForClassLoader(){
        Collection<URL> urls= ClassPaths.forClassLoader();
        int i=0;
        for (URL url:urls){
            i++;
            System.out.print(i+" ");
            System.out.println(url.getPath());
        }




    }

    @Test
    public void testForJavaClassPath(){

        Collection<URL> urls1= ClassPaths.forJavaClassPath();
        int i=0;
        for (URL url:urls1){
            i++;
            System.out.print(i+" ");
            System.out.println(url);
        }

    }

}
