package com.logzc.webzic.dynamic.fs;

import java.io.IOException;
import java.io.InputStream;

/**
 * File in this ZicFs. Forget the name.
 * Created by lishuang on 2016/7/17.
 */
public interface ZicFile {
    String getName();
    String getRelativePath();
}
