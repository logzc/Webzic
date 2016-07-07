package com.logzc.webzic.demo.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lishuang on 2016/7/7.
 */
@WebServlet("/classloader")
public class ClassLoaderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        ClassLoader loader = this.getClass().getClassLoader();
        while(loader != null) {
            out.write(loader.getClass().getName()+"<br/>");
            loader = loader.getParent();
        }
        out.write(String.valueOf(loader));
        out.flush();
        out.close();
    }

}
