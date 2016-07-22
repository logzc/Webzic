package com.logzc.webzic.demo;

import com.lish.fork.javassist.VisibleAn;
import com.logzc.webzic.reflection.Reflections;
import com.logzc.webzic.reflection.scanner.TypeAnnotationScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lishuang on 2016/7/7.
 */
@VisibleAn
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) throws Exception {

        TypeAnnotationScanner scanner=new TypeAnnotationScanner(VisibleAn.class);
        Reflections.scan(scanner);


        //scan urls that contain 'my.package', include inputs starting with 'my.package', use the default scanners
//        Reflections reflections = new Reflections("com");
//        //TypeAnnotationsScanner
//        Set<Class<?>> singletons =
//                reflections.getTypesAnnotatedWith(VisibleAn.class);
//        System.out.println("-----------------------");
//        for (Class<?> clazz:singletons ){
//            System.out.println(clazz);
//        }



//        Set<Class<?>> singletons1 =
//                reflections.getTypesAnnotatedWith(InvisibleAn.class);
//        for (Class<?> clazz:singletons1 ){
//
//            System.err.println(clazz);
//
//        }



        //testJarProtocol();

        //Collection<URL> urls= ClassPaths.forPackage("com.lish");
//        Collection<URL> urls=ClassPaths.forJavaClassPath();
        //Collection<URL> urls=ClassPaths.forClassLoader();

//        for (URL url:urls){
//            if(url.getPath().contains("practice-1.0-SNAPSHOT.jar")){
//                System.out.println(url);
//
//                ZicDir zicDir=ZicDir.fromURL(url);
//
//                Collection<ZicFile> zicFiles=zicDir.getFiles();
//
//                for (ZicFile file:zicFiles){
//                    System.out.println(file.getRelativePath());
//                }
//            }
//
//        }



//        Collection<URL> urls1=ClassPaths.forJavaClassPath();
//
//        for (URL url:urls1){
//            System.out.println(url);
//        }
    }
}
