package com.logzc.webzic.web.controller;

import com.logzc.webzic.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * default access control controller. WITHOUT @RestController Annotation.
 * Created by lishuang on 2016/7/25.
 */
@RequestMapping(path = WebzicPath.WEBZIC)
public class AccessController {


    //http code: 403.
    @RequestMapping(path = WebzicPath.METHOD_NOT_ALLOWED)
    public Map<String, ?> methodNotAllowed() {

        Map<String, String> map = new HashMap<>();
        map.put("type", "ERROR");
        map.put("message", "Method not allowed");

        return map;
    }
}
