package com.logzc.webzic;

import com.logzc.webzic.clazz.Foo;
import com.logzc.webzic.dynamic.ClassPaths;
import com.logzc.webzic.dynamic.fs.JarInputDir;
import com.logzc.webzic.dynamic.fs.ZicDir;
import com.logzc.webzic.dynamic.fs.ZicFile;

import java.io.File;
import java.net.URL;
import java.util.Collection;
import java.util.Enumeration;

/**
 * Created by lishuang on 2016/7/7.
 */
public class HelloWorld {


    public static void testJarProtocol() throws Exception{
        URL url=new URL("jar:file:/D:/Group/Logzc/Webzic/library/libs/practice-1.0-SNAPSHOT.jar!/");

        JarInputDir jarInputDir=new JarInputDir(url);
        jarInputDir.getFiles().forEach(zicFile -> {
            System.out.println(zicFile.getRelativePath());
        });
    }

    public static void main(String[] args) throws Exception{

        //testJarProtocol();

        Collection<URL> urls=ClassPaths.forPackage("com.logzc");
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

        System.out.println("-----------------------");

//        Collection<URL> urls1=ClassPaths.forJavaClassPath();
//
//        for (URL url:urls1){
//            System.out.println(url);
//        }



    }
}
