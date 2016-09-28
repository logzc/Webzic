package com.logzc.webzic.util;

import org.junit.Assume;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lishuang on 2016/9/28.
 */
public class RegexTest {

    @Test
    public void testInjectTemplate(){

        String template="${jdbc.url}";

        //start with "${" and end with "}"
        Pattern pattern = Pattern.compile("^\\$\\{(.*?)\\}$");
        Matcher matcher = pattern.matcher(template);

        Assume.assumeTrue(matcher.find());
        if (matcher.find())
        {
            //${jdbc.url}
            Assume.assumeTrue("${jdbc.url}".equals(matcher.group(0)));
            Assume.assumeTrue("jdbc.url".equals(matcher.group(1)));
        }

    }
}
