package com.logzc.webzic.dynamic.fs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * File in my System.
 * Created by lishuang on 2016/7/17.
 */
public class SystemFile extends ZicFile {

    private final SystemDir root;
    private final File file;

    public SystemFile(SystemDir root, File file) {
        this.root = root;
        this.file = file;
    }

    @Override
    public String getName() {
        return file.getName();
    }

    @Override
    public String getRelativePath() {

        String filepath = file.getPath().replace("\\", "/");
        if (filepath.startsWith(root.getPath())) {
            return filepath.substring(root.getPath().length() + 1);
        }
        throw new RuntimeException("SystemFile's root and file not match.");
    }

    public InputStream openInputStream() throws IOException {

        return new FileInputStream(file);
    }

    @Override
    public String toString() {
        return file.toString();
    }
}
