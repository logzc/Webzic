package com.logzc.webzic.demo;

import com.logzc.webzic.annotation.RequestMapping;
import com.logzc.webzic.annotation.RestController;
import com.logzc.webzic.demo.controller.UserController;
import com.logzc.webzic.util.ClassUtil;
import com.logzc.webzic.util.PropertyUtil;
import org.apache.commons.lang3.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lishuang on 2016/7/7.
 */
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args){
        ClassUtil.getClassSet("com.logzc");
    }
}
