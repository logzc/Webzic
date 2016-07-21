package com.logzc.webzic.web.pool;

import com.logzc.webzic.annotation.RestController;
import com.logzc.webzic.dynamic.scanner.Scanner;
import com.logzc.webzic.dynamic.scanner.TypeAnnotationScanner;

import java.util.Map;

/**
 * This is a pool containers all the @Controller.
 * Created by lishuang on 2016/7/19.
 */
public class ControllerPool extends Pool {

    private Map<String,String> map;

    /**
     * init the request mapping.
     */
    @Override
    public void init(){

    }


    @Override
    public Scanner getScanner() {

        if (scanner == null) {
            scanner = new TypeAnnotationScanner(RestController.class);
        }

        return scanner;
    }
}
