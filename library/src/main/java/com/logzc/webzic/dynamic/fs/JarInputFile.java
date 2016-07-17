package com.logzc.webzic.dynamic.fs;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.zip.ZipEntry;

/**
 * jar file.
 * Created by lishuang on 2016/7/17.
 */
public class JarInputFile implements ZicFile {
    private final JarEntry entry;
    private final JarInputDir jarInputDir;

    public JarInputFile(JarEntry entry, JarInputDir jarInputDir) {
        this.entry = entry;
        this.jarInputDir = jarInputDir;
    }

    @Override
    public String getName() {
        String name = entry.getName();
        return name.substring(name.lastIndexOf("/") + 1);
    }

    @Override
    public String getRelativePath() {
        return entry.getName();
    }


    @Override
    public String toString() {
        return jarInputDir.getPath() + "!" + java.io.File.separatorChar + entry.toString();
    }
}