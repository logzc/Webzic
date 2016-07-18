package com.logzc.webzic.dynamic.fs;

import java.io.IOException;
import java.io.InputStream;

/**
 * File in this ZicFs. Forget the name.
 * Created by lishuang on 2016/7/17.
 */
public abstract class ZicFile {
    public abstract String getName();
    public abstract String getRelativePath();

    public abstract InputStream openInputStream() throws IOException;
}
