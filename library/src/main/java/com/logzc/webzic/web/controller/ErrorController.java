package com.logzc.webzic.web.controller;

import com.logzc.webzic.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * default error controller. WITHOUT @RestController Annotation.
 * Created by lishuang on 2016/7/25.
 */
@RequestMapping(path = WebzicPath.WEBZIC)
public class ErrorController {

    @RequestMapping(path = WebzicPath.ERROR)
    public Map<String, ?> error1(String message,int code) {

        Map<String, String> map = new HashMap<>();
        map.put("type", "ERROR");
        map.put("message", message);

        return map;
    }
}
