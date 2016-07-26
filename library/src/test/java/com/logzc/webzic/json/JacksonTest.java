package com.logzc.webzic.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lishuang on 2016/7/26.
 */
public class JacksonTest {


    @Test
    public void testWriteToString() throws IOException {
        ArrayList<String> lists = new ArrayList<>();
        lists.add("testlist01");
        lists.add("testlist02");
        HashMap<String, String> maps = new HashMap<>();
        maps.put("mapkey01", "mapvalue01");
        maps.put("mapkey02", "mapvalue02");
        JsonBean1 user1 = new JsonBean1();
        user1.info = lists;
        user1.map = maps;
        user1.name = "webzic";
        user1.age = 13;
        user1.ok = false;
        user1.height = 1.83f;

        ObjectMapper mapper = new ObjectMapper();
        //转换的时候非常依赖于Bean的Setter和Getter.
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user1);
        // 将字符串美化成多行
        System.out.println(json);

        String json1 = mapper.writeValueAsString(user1);
        System.out.println(json1);

    }

    @Test
    public void testWriteToFile() throws IOException {

        ArrayList<String> lists = new ArrayList<>();
        lists.add("testlist01");
        lists.add("testlist02");
        HashMap<String, String> maps = new HashMap<>();
        maps.put("mapkey01", "mapvalue01");
        maps.put("mapkey02", "mapvalue02");
        JsonBean1 user1 = new JsonBean1();
        user1.info = lists;
        user1.map = maps;
        user1.name = "webzic";
        user1.age = 13;
        user1.ok = false;
        user1.height = 1.83f;
        ObjectMapper mapper = new ObjectMapper();

        String filePath = "C:/lish/Group/Logzc/Webzic/library/src/test/java/com/logzc/webzic/json/user1.json";
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), user1);
    }


    @Test
    public void testJsonToObject(){
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "{\"name\":\"webzic\",\"age\":13,\"ok\":false,\"info\":[\"testlist01\",\"testlist02\"],\"map\":{\"mapkey01\":\"mapvalue01\",\"mapkey02\":\"mapvalue02\"},\"height\":1.83}";
        JsonBean1 user;
        try {
            user = mapper.readValue(jsonString, JsonBean1.class);
            System.out.println(user.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void testFileJsonToObject(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            String filePath = "C:/lish/Group/Logzc/Webzic/library/src/test/java/com/logzc/webzic/json/user1.json";
            JsonBean1 user1 = mapper.readValue(new File(filePath), JsonBean1.class);
            System.out.println(user1.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Test
    public void testJsonIgnore() throws IOException {
        ArrayList<String> lists = new ArrayList<>();
        lists.add("testlist1");
        lists.add("testlist2");
        HashMap<String, String> maps = new HashMap<>();
        maps.put("mapkey1", "mapvalue01");
        maps.put("mapkey2", "mapvalue02");
        JsonBean2 user1 = new JsonBean2();
        user1.info = lists;
        user1.map = maps;
        user1.name = "webzic";
        user1.age = 14;
        user1.ok = false;
        user1.height = 1.83f;

        ObjectMapper mapper = new ObjectMapper();
        //转换的时候非常依赖于Bean的Setter和Getter.
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user1);
        // 将字符串美化成多行
        System.out.println(json);

        String json1 = mapper.writeValueAsString(user1);
        System.out.println(json1);

    }


}
