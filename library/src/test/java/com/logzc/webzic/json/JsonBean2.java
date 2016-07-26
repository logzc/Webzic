package com.logzc.webzic.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by lishuang on 2016/7/26.
 */
@Data
public class JsonBean2 {


    String name;

    @JsonIgnore
    int age;
    boolean ok;
    List<String> info;
    Map<String,String> map;
    float height;


}
