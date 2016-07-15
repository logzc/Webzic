package com.lish.fork.hello;

import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Launcher;

import java.net.URL;

/**
 * Created by lishuang on 2016/5/23.
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        URL[] urls = Launcher.getBootstrapClassPath().getURLs();

        for (URL url : urls) {
            System.out.println(url.toExternalForm());
        }

        System.out.println(SystemUtils.JAVA_CLASS_PATH);
        System.out.println(SystemUtils.JAVA_LIBRARY_PATH);

    }
}
