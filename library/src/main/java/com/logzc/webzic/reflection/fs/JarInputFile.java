package com.logzc.webzic.reflection.fs;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * jar file.
 * Created by lishuang on 2016/7/17.
 */
public class JarInputFile extends ZicFile {
    private final JarInputDir jarInputDir;
    private final JarFile jarFile;
    private final JarEntry entry;


    public JarInputFile(JarInputDir jarInputDir, JarFile jarFile, JarEntry entry) {
        this.jarInputDir = jarInputDir;
        this.jarFile = jarFile;
        this.entry = entry;

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
    public InputStream openInputStream() throws IOException {

        InputStream fis = jarFile.getInputStream(entry);

        //TODO:when to close the InputStream. It's a problem.
        
        return fis;

    }


    @Override
    public String toString() {

        return jarInputDir.getPath() + "!" + java.io.File.separatorChar + entry.toString();


    }

}
