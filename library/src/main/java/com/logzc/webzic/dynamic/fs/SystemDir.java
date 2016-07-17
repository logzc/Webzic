package com.logzc.webzic.dynamic.fs;

import java.io.File;
import java.util.Objects;

/**
 * Created by lishuang on 2016/7/17.
 */
public class SystemDir implements FS.Dir {

    //This is the Directory File in system.
    private final File file;

    public SystemDir(File file){
        if(file!=null &&(!file.isDirectory()||!file.canRead())){
            throw new RuntimeException("Cannot use dir "+file);
        }
        this.file=file;
    }

    @Override
    public String getPath() {

        Objects.requireNonNull(file);

        return file.getPath().replace("\\","/");
    }

    @Override
    public Iterable<FS.File> getFiles() {
        return null;
    }

    @Override
    public void close() {

    }
}
