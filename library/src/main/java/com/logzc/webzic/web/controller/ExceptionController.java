package com.logzc.webzic.web.controller;

import com.logzc.webzic.annotation.RequestMapping;

import java.util.Map;

/**
 * default exception controller.  WITHOUT @RestController Annotation.
 * Created by lishuang on 2016/7/25.
 */
@RequestMapping(path = WebzicPath.WEBZIC)
public class ExceptionController {

    @RequestMapping(path = WebzicPath.EXCEPTION)
    public Map<String, ?> exception() {

        return null;
    }
}
