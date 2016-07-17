package com.logzc.webzic.dynamic.fs;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * This is a special File System.
 * Created by lishuang on 2016/7/17.
 */
public class FS {

    /**
     * Directory in this FS.
     */
    public interface Dir {
        String getPath();

        Iterable<File> getFiles();

        void close();
    }

    /*
     * File in this FS.
     */
    public interface File{
        String getName();
        String getRelativePath();
        InputStream openInputStream() throws IOException;
    }

}
