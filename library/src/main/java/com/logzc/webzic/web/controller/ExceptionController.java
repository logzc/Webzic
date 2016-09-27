package com.logzc.webzic.web.controller;

import com.logzc.webzic.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * default exception controller.  WITHOUT @RestController Annotation.
 * Created by lishuang on 2016/7/25.
 */
@RequestMapping(path = WebzicPath.WEBZIC)
public class ExceptionController {

    @RequestMapping(path = WebzicPath.EXCEPTION)
    public Map<String, ?> exception(HttpServletRequest request, Exception e) {

        /*
        {
            "timestamp": 1474960725985,
                "status": 400,
                "error": "Bad Request",
                "exception": "org.springframework.web.bind.MissingServletRequestParameterException",
                "message": "Required Date parameter 'date' is not present",
                "path": "/foo/detail"
        }
        */

        Map<String,Object> map=new LinkedHashMap<>();
        map.put("timestamp",System.currentTimeMillis());
        map.put("status",400);
        map.put("error","Exception");
        map.put("exception",e.getClass().getName());
        map.put("message",e.getMessage());
        map.put("path",request.getRequestURI());

        return map;
    }
}
