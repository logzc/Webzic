package com.logzc.webzic.reflection.fs;

import com.logzc.webzic.util.FileUtil;

import java.io.File;
import java.net.URL;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A wrapper of system file.
 * Created by lishuang on 2016/7/17.
 */
public class SystemDir extends ZicDir {

    private final URL url;

    //This is the Directory File in system.
    private final File file;

    public SystemDir(URL url) {

        String packagePath = url.getPath().replaceAll("%20", " ");
        this.url=url;
        this.file=new File(packagePath);

        if ((!file.isDirectory() || !file.canRead())) {
            throw new RuntimeException("Cannot use dir " + file);
        }
    }

    @Override
    public String getPath() {

        Objects.requireNonNull(file);

        return file.getPath().replace("\\", "/");
    }

    @Override
    public Collection<ZicFile> getFiles() {

        Collection<File> files = FileUtil.listFiles(file, null, true);

        return files.stream().map(file1 -> new SystemFile(this, file1)).collect(Collectors.toList());
    }


    @Override
    public String toString() {

        return file.toString();
    }
}
