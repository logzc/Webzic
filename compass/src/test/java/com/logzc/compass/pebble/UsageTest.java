package com.logzc.compass.pebble;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lishuang on 2016/10/26.
 */
public class UsageTest {


    @Test
    public void test1() throws PebbleException, IOException {
        PebbleEngine engine = new PebbleEngine.Builder().build();
        PebbleTemplate compiledTemplate = engine.getTemplate("home.html");

        Map<String, Object> context = new HashMap<>();
        context.put("name", "Mitchell");

        Writer writer = new StringWriter();
        compiledTemplate.evaluate(writer, context);

        String output = writer.toString();

        System.out.println(output);
    }
}
