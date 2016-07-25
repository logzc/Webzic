package com.logzc.webzic.web.controller;

import com.logzc.webzic.annotation.RequestMapping;

import java.util.Map;

/**
 * default exception controller.  WITHOUT @RestController Annotation.
 * Created by lishuang on 2016/7/25.
 */
@RequestMapping(path = "/webzic")
public class ExceptionController {

    @RequestMapping(path = "/exception")
    public Map<String,?> exception(){

        return null;
    }
}
