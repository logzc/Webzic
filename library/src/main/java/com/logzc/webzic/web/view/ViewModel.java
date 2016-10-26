package com.logzc.webzic.web.view;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lishuang on 2016/10/26.
 */
@Data
public class ViewModel {

    private String view;

    private Map<String,Object> model;

    public ViewModel(){
        this.model=new HashMap<>();
    }

}
