package com.logzc.webzic.pool;

import com.logzc.webzic.dynamic.scanner.Scanner;

/**
 * Created by lishuang on 2016/7/19.
 */
public abstract class Pool {
    public abstract void init();
    protected Scanner scanner;
    public abstract Scanner getScanner();
}
