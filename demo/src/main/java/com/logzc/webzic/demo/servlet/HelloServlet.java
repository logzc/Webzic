package com.logzc.webzic.demo.servlet;

import com.lish.fork.javassist.InvisibleAn;
import com.lish.fork.javassist.VisibleAn;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lishuang on 2016/7/7.
 */
@VisibleAn
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    static {
        System.out.println("You have loaded me.");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = dateFormat.format(new Date());
        System.out.println("Hello, this is a servlet");

        req.setAttribute("currentTime", currentTime);
        req.getRequestDispatcher("/hello/hello.jsp").forward(req, resp);
    }
}