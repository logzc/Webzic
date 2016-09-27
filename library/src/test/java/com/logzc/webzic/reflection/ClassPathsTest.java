package com.logzc.webzic.reflection;

import com.logzc.webzic.reflection.fs.JarInputDir;
import com.logzc.webzic.reflection.fs.ZicDir;
import com.logzc.webzic.reflection.fs.ZicFile;
import org.junit.Assume;
import org.junit.Test;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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

    @Test
    public void testForPackage(){
        //testJarProtocol();

        Collection<URL> urls=ClassPaths.forPackage("com");
        //Collection<URL> urls=ClassPaths.forJavaClassPath();
        //Collection<URL> urls=ClassPaths.forClassLoader();

        for (URL url:urls){
            System.out.println(url);
            System.out.println(url.getProtocol());

            ZicDir zicDir=ZicDir.fromURL(url);

            Collection<ZicFile> zicFiles=zicDir.getFiles();

            for (ZicFile file:zicFiles){
                System.out.println(file.getRelativePath());
            }


        }
    }


    @Test
    public void testJarProtocol() throws Exception{
        URL url=new URL("jar:file:/C:/lish/Group/Logzc/Webzic/demo/libs/fork-1.0.jar!/");

        JarInputDir jarInputDir=new JarInputDir(url);
        jarInputDir.getFiles().forEach(zicFile -> {
            System.out.println(zicFile.getRelativePath());
        });
    }


    @Test
    public void testAssignable() throws Exception{


        ArrayList arrayList=new ArrayList();

        Assume.assumeTrue(List.class.isAssignableFrom(arrayList.getClass()));
        Assume.assumeTrue(List.class.isAssignableFrom(List.class));


        Integer[] a=new Integer[]{1,2,3};
        Number[] b=(Number[])a;

        System.out.println(b);

        List<Integer> c=new ArrayList<>();
        c.add(1);
        List<Number> d=new ArrayList<>();

    }


}
