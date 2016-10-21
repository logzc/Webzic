package com.logzc.common.util;

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

}
