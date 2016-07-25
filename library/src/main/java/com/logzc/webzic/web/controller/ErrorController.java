package com.logzc.webzic.web.controller;

import com.logzc.webzic.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * default error controller. WITHOUT @RestController Annotation.
 * Created by lishuang on 2016/7/25.
 */
@RequestMapping(path = "/webzic")
public class ErrorController {

    @RequestMapping(path = "/error")
    public Map<String, ?> error(String message) {

        Map<String, String> map = new HashMap<>();
        map.put("type", "ERROR");
        map.put("message", message);

        return map;
    }
}
