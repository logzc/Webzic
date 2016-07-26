package com.logzc.webzic.json;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by lishuang on 2016/7/26.
 */
@Data
public class JsonBean1{
    String name;
    int age;
    boolean ok;
    List<String> info;
    Map<String,String> map;
    float height;


}
