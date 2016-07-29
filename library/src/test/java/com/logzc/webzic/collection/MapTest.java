package com.logzc.webzic.collection;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by lishuang on 2016/7/29.
 */
public class MapTest {

    @Test
    public void testMapBean() {

        Map<String, MapBean0> map1 = new HashMap<>();
        Map<String, MapBean0> map2 = new HashMap<>();

        MapBean0 mapBean0 = new MapBean0();
        mapBean0.name = "lishuang";

        map1.put("wo", mapBean0);

        System.out.println(map1);

        map2.put("ni", map1.get("wo"));
        System.out.println(map2);

        MapBean0 mapBean01=map2.get("ni");
        mapBean01.name="haha";

        System.out.println(map1);
        System.out.println(map2);


    }

    @Test
    public void testWeakHashMap() {
        String a = "a";
        String b = "b";
        Map<String, String> weakmap = new WeakHashMap<>();
        weakmap.put(a, "aaa");
        weakmap.put(b, "bbb");


        Map<String, String> map = new HashMap<>();
        map.put(a, "aaa");
        map.put(b, "bbb");

        map.remove(a);

        a = null;
        b = null;

        System.gc();
        for (Object o : map.entrySet()) {
            Map.Entry en = (Map.Entry) o;
            System.out.println("map:" + en.getKey() + ":" + en.getValue());
        }

        for (Object o : weakmap.entrySet()) {
            Map.Entry en = (Map.Entry) o;
            System.out.println("weakmap:" + en.getKey() + ":" + en.getValue());

        }
    }
}
