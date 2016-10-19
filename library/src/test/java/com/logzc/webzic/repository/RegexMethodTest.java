package com.logzc.webzic.repository;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lishuang on 2016/10/19.
 */
public class RegexMethodTest {

    @Test
    public void regexTest() {

        // 按指定模式在字符串查找
        String line = "queryByUsername";
        String pattern = "^queryBy(.+)$";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        if (m.find()) {
            System.out.println("String: " + m.group(0));

            String clause = m.group(1);
            String[] ands=clause.split("And");

            for (String and:ands){
                System.out.println("-> "+and);
            }

        } else {
            System.out.println("NO MATCH");
        }

    }


}
