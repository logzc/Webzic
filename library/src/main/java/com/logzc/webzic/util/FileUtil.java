package com.logzc.webzic.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Collection;

/**
 * File common methods.
 * Wrap many methods in apache commons.
 * Created by lishuang on 2016/7/17.
 */
public class FileUtil {

    @SuppressWarnings("unchecked")
    public static Collection<File> listFiles(File directory, String[] extensions, boolean recursive) {

        return FileUtils.listFiles(directory, extensions, recursive);
    }


    public static void main(String[] args) throws Exception {

        System.out.println("HelloWorld");

        File dir=new File("D:\\Study\\Java\\ebook");


        System.out.println(listFiles(dir,new String[]{"java"},false));

    }
}
