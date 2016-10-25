package com.logzc.webzic.web.core;

import com.logzc.webzic.json.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This is the  manager of output to client.
 * Created by lishuang on 2016/9/30.
 */
public class OutputManager {

    public void output(HttpServletRequest request, HttpServletResponse response, Object result) {
        //check the type of result.
        String jsonResult = JsonUtil.toJson(result);

        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.println(jsonResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
