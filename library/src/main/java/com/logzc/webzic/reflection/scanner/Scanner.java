package com.logzc.webzic.reflection.scanner;

import com.logzc.webzic.reflection.fs.ZicFile;

import java.util.List;

/**
 * abstract base Scanner class.
 * Created by lishuang on 2016/7/18.
 */
public abstract class Scanner {
    public abstract void accept(ZicFile zicFile);
    public abstract List<String> getClassNames();
}
