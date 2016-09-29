package com.logzc.webzic;

import com.logzc.webzic.bean.AppContext;
import com.logzc.webzic.web.core.HandlerMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        try {
            AppContext.init();
        } catch (Exception e) {
            throw new ServletException(e);
        }

    }


    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) {

        HandlerMethod handlerMethod = AppContext.getHandlerMethodManager().get(request);

        handlerMethod.handle(request, response);

    }

    @Override
    public void destroy() {
        logger.debug("----------destroy()------------");
    }


}
