package com.logzc.webzic.dynamic;

import com.logzc.webzic.dynamic.fs.JarInputDir;
import com.logzc.webzic.dynamic.fs.ZicDir;
import com.logzc.webzic.dynamic.fs.ZicFile;
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

}
