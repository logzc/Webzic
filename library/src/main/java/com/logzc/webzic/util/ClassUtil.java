package com.logzc.webzic.util;

import com.logzc.webzic.clazz.Foo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * This is the class for loading classes.
 * Created by lishuang on 2016/7/7.
 */
public class ClassUtil {
    private static final Logger logger = LoggerFactory.getLogger(ClassUtil.class);

    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    public static Class<?> loadClass(String className, boolean isInitialized) {

        Class<?> clazz;
        try {
            clazz = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            logger.debug("Cannot find " + className);
            throw new RuntimeException();
        }
        return clazz;
    }

    public static Class<?> loadClass(String className) {
        return loadClass(className, true);
    }

    //Load classes in the specific package.
    public static Set<Class<?>> getClassSet(String packageName) {
        Set<Class<?>> classSet = new HashSet<>();

        try {
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));

            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    String protocol = url.getProtocol();
                    if (protocol.equals("file")) {
                        String packagePath = url.getPath().replaceAll("%20", " ");
                        addClass(classSet,packagePath,packageName);

                    } else if (protocol.equals("jar")) {

                    }
                }
            }

        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return null;
    }

    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
        logger.debug(packagePath);
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isFile() && file.getName().endsWith(".class") || file.isDirectory();
            }
        });

        if(files!=null){
            for (File file : files) {
                String fileName = file.getName();
                if (file.isFile()) {
                    String className = fileName.substring(0, fileName.lastIndexOf("."));
                    if (StringUtils.isNotEmpty(packageName)) {
                        className = packageName + "." + className;
                    }
                    doAddClass(classSet, className);
                } else {
                    String subPackagePath = fileName;
                    if (StringUtils.isNotEmpty(packagePath)) {
                        subPackagePath = packagePath + "/" + subPackagePath;
                    }
                    String subPackageName = fileName;
                    if (StringUtils.isNotEmpty(packageName)) {
                        subPackageName = packageName + "." + subPackageName;
                    }

                    //recursive to add all classes.
                    addClass(classSet, subPackagePath, subPackageName);
                }
            }
        }


    }

    private static void doAddClass(Set<Class<?>> classSet, String className) {
        logger.debug(className+" ---------- loading");
        Class<?> cls = loadClass(className, false);
        classSet.add(cls);
    }

    public static void main(String[] args) throws Exception {

        getClassSet("com.logzc");

    }

}
