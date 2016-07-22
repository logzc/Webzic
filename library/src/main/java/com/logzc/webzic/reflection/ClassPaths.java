package com.logzc.webzic.reflection;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

/**
 * this package is mainly for dynamic class loading.
 * You can get classpath by this Class.
 * Created by lishuang on 2016/7/16.
 */
public class ClassPaths {


    private static final Logger logger = LoggerFactory.getLogger(ClassPaths.class);

    /**
     * Get the context classLoader.
     *
     * @return the context class loader, may be null.
     */
    public static ClassLoader contextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * Get the current class's classLoader.
     */
    public static ClassLoader staticClassLoader() {
        return ClassPaths.class.getClassLoader();
    }

    /**
     * Returns an array of classLoaders by the specified array.
     * If input is null or empty, returns contextClassLoader and staticClassLoader.
     */
    public static ClassLoader[] classLoaders(ClassLoader... classLoaders) {
        if (classLoaders != null && classLoaders.length != 0) {
            return classLoaders;
        } else {
            ClassLoader contextClassLoader = contextClassLoader();
            ClassLoader staticClassLoader = staticClassLoader();

            if (contextClassLoader == null) {
                if (staticClassLoader == null) {
                    return new ClassLoader[]{};
                } else {
                    return new ClassLoader[]{staticClassLoader};
                }
            } else {
                if (staticClassLoader == null) {
                    return new ClassLoader[]{contextClassLoader};
                } else {
                    if (contextClassLoader == staticClassLoader) {
                        return new ClassLoader[]{staticClassLoader};
                    } else {
                        return new ClassLoader[]{contextClassLoader, staticClassLoader};
                    }

                }
            }
        }
    }


    /**
     * Get the urls based on the specified resourceName.
     * When resourceName is "", only urls with file: protocol can be detected.
     */
    public static Collection<URL> forResource(String resourceName, ClassLoader... classLoaders) {
        List<URL> result = new ArrayList<>();
        ClassLoader[] loaders = classLoaders(classLoaders);
        for (ClassLoader classLoader : loaders) {

            try {
                Enumeration<URL> urls = classLoader.getResources(resourceName);

                while (urls.hasMoreElements()) {
                    URL url = urls.nextElement();
                    int index = url.toExternalForm().lastIndexOf(resourceName);
                    if (index != -1) {
                        result.add(new URL(url.toExternalForm().substring(0, index)));
                    } else {
                        result.add(url);
                    }


                }
            } catch (IOException e) {

                logger.debug("Error getting resources for " + resourceName, e);
            }

        }

        return distinctUrls(result);
    }

    /**
     * Return a collection of URLs based on a package name. The packageName can be ""
     */
    public static Collection<URL> forPackage(String packageName, ClassLoader... classLoaders) {

        return forResource(resourceName(packageName),classLoaders);

    }




    /**
     * Get the URL which contains the clazz.
     * @param clazz Class<?>
     * @param classLoaders may be null.
     * @return the url contains the clazz.
     */
    public static URL forClass(Class<?> clazz,ClassLoader... classLoaders){
        ClassLoader[] loaders=classLoaders(classLoaders);
        String resourceName=clazz.getName().replace(".","/")+".class";
        for (ClassLoader classLoader:loaders){
            URL url=classLoader.getResource(resourceName);
            if(url!=null){
                String normalizedUrl=url.toExternalForm().substring(0,url.toExternalForm().lastIndexOf(clazz.getPackage().getName().replace(".","/")));
                try {
                    return new URL(normalizedUrl);
                } catch (MalformedURLException e) {
                    logger.debug("Cannot get the URL",e);
                }
            }
        }
        return null;
    }


    /**
     * Get the URLs from classloaders.
     */
    public static Collection<URL> forClassLoader(ClassLoader... classLoaders){
        Collection<URL> result=new ArrayList<URL>();

        ClassLoader[] loaders=classLoaders(classLoaders);
        for(ClassLoader classLoader:loaders){
            while (classLoader!=null){
                if(classLoader instanceof URLClassLoader){
                    URL[] urls=((URLClassLoader)classLoader).getURLs();
                    if(urls!=null){
                        result.addAll(Arrays.asList(urls));
                    }
                }
                classLoader=classLoader.getParent();
            }
        }

        return distinctUrls(result);
    }


    /**
     * Get the urls based on the java.class.path system property.
     */
    public static Collection<URL> forJavaClassPath(){
        Collection<URL> urls=new ArrayList<>();
        String javaClassPath=System.getProperty("java.class.path");
        if(javaClassPath!=null){
            String[] pathStrings=javaClassPath.split(File.pathSeparator);
            for (String path:pathStrings){
                try {
                    urls.add(new File(path).toURI().toURL());
                } catch (MalformedURLException e) {

                    logger.debug("Cannot get the URL",e);
                }
            }
        }

        return distinctUrls(urls);
    }

    /**
     * Make the urls in the collection distinct.
     */
    private static Collection<URL> distinctUrls(Collection<URL> urls) {
        Map<String, URL> distinct = new HashMap<>(urls.size());
        for (URL url:urls){
            distinct.put(url.toExternalForm(),url);
        }
        return distinct.values();
    }

    private static String resourceName(String name){
        if(name!=null){
            String resourceName=name.replace(".","/");
            resourceName=resourceName.replace("/","/");
            if (resourceName.startsWith("/")){
                resourceName=resourceName.substring(1);
            }
            return resourceName;
        }
        return null;
    }


}
