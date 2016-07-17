package com.logzc.webzic.dynamic.fs;

/**
 * Created by lishuang on 2016/7/17.
 */
public interface ZicDir {
    String getPath();

    Iterable<ZicFile> getFiles();

    void close();
}
