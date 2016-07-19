package com.logzc.webzic.pool;

import com.logzc.webzic.annotation.RestController;
import com.logzc.webzic.dynamic.scanner.Scanner;
import com.logzc.webzic.dynamic.scanner.TypeAnnotationScanner;

/**
 * This is a pool containers all the @Controller.
 * Created by lishuang on 2016/7/19.
 */
public class ControllerPool extends Pool {

    @Override
    public Scanner getScanner() {

        if (scanner == null) {
            scanner = new TypeAnnotationScanner(RestController.class);
        }

        return scanner;
    }
}
