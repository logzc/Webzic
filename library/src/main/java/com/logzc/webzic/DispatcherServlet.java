package com.logzc.webzic;

import com.logzc.webzic.web.pool.BeanFactoryManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This servlet will intercept all the http request.
 * Created by lishuang on 2016/7/19.
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        logger.debug("----------init(ServletConfig servletConfig)------------");

        //init all the annotation beans.
        BeanFactoryManager.init();

    }


    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //BeanFactoryManager.getControllerBeanFactory().





        // 设置响应内容类型
        response.setContentType("text/html");

        logger.debug(request.getMethod());

        // 实际的逻辑是在这里
        PrintWriter out = response.getWriter();
        out.println("<h1>Hello Webzic</h1>");
    }

    @Override
    public void destroy() {
        logger.debug("----------destroy()------------");
    }


}
