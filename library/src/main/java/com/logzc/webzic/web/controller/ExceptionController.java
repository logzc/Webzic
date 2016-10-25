package com.logzc.webzic.web.controller;

import com.logzc.webzic.annotation.RequestMapping;
import com.logzc.webzic.exception.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * default exception controller.  WITHOUT @RestController Annotation.
 * Created by lishuang on 2016/7/25.
 */
@RequestMapping(path = WebzicPath.WEBZIC)
public class ExceptionController {

    @RequestMapping(path = WebzicPath.EXCEPTION)
    public Map<String, ?> exception(HttpServletRequest request, HttpServletResponse response, Exception e) {

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

        int status = 500;
        String error = "Internal Error";
        if (e instanceof NotFoundException) {
            status = 404;
            error = "404 Not Found";
        }
        response.setStatus(status);

        e.printStackTrace();


        Map<String, Object> map = new LinkedHashMap<>();
        map.put("timestamp", System.currentTimeMillis());
        map.put("status", status);
        map.put("error", error);
        map.put("exception", e.getClass().getSimpleName());
        map.put("message", e.getMessage());
        map.put("path", request.getRequestURI());

        return map;
    }
}
