package com.logzc.webzic.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lishuang on 2016/7/26.
 */
public class JacksonTest {


    @Test
    public void testDatabind() throws IOException{
        ArrayList<String> lists = new ArrayList<>();
        lists.add("testlist01");
        lists.add("testlist02");
        HashMap<String,String> maps = new HashMap<>();
        maps.put("mapkey01","mapvalue01");
        maps.put("mapkey02","mapvalue02");
        JsonBean1 user1 = new JsonBean1();
        user1.info = lists;
        user1.map=maps;
        user1.name="webzic";
        user1.age=13;
        user1.ok=false;
        user1.height=1.83f;

        ObjectMapper mapper = new ObjectMapper();
        // 仅输出一行json字符串
        mapper.writeValue(System.out, user1);

        // 将字符串美化成多行
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user1));
    }

}
