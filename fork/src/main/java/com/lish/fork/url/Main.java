package com.lish.fork.url;

import org.apache.commons.io.FilenameUtils;

/**
 * Created by lishuang on 2016/5/23.
 */
public class Main {

    public static void main(String[] args) {
        String url = "http://www.example.com/some/path/to/a/file.xml";

        String baseName = FilenameUtils.getBaseName(url);
        String extension = FilenameUtils.getExtension(url);
        String fullName=FilenameUtils.getName(url);

        System.out.println("Basename : " + baseName);
        System.out.println("extension : " + extension);
        System.out.println("fullName : " + fullName);
    }
}
