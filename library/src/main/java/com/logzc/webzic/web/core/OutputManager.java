package com.logzc.webzic.web.core;

import com.logzc.webzic.json.JsonUtil;
import com.logzc.webzic.web.view.ViewModel;
import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.loader.ClasspathLoader;
import com.mitchellbosecke.pebble.loader.Loader;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * This is the  manager of output to client.
 * Created by lishuang on 2016/9/30.
 */
public class OutputManager {

    public void output(HttpServletRequest request, HttpServletResponse response, Object result) {
        String content=null;
        if (result instanceof ViewModel) {

            try{
                ViewModel viewModel= (ViewModel) result;

                Loader<?> loader = new ClasspathLoader();
                loader.setPrefix("../../WEB-INF");

                PebbleEngine engine = new PebbleEngine.Builder().loader(loader).build();
                PebbleTemplate compiledTemplate = engine.getTemplate(viewModel.getView());

                Writer writer = new StringWriter();
                compiledTemplate.evaluate(writer, viewModel.getModel());

                content = writer.toString();

                System.out.println(content);

                response.setContentType("text/html");

            }catch (Exception e){
                content="Template Error!";
                e.printStackTrace();
            }



        } else {
            //check the type of result.
            content = JsonUtil.toJson(result);
            response.setContentType("text/json");
        }


        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
