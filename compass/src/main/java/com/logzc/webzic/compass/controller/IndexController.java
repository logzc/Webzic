package com.logzc.webzic.compass.controller;

import com.logzc.webzic.annotation.Autowired;
import com.logzc.webzic.annotation.RequestMapping;
import com.logzc.webzic.annotation.RestController;
import com.logzc.webzic.compass.dao.UserDao;
import com.logzc.webzic.compass.model.User;
import com.logzc.webzic.compass.service.UserService;
import com.logzc.webzic.web.core.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lishuang on 2016/7/19.
 */
@RestController
public class IndexController {

    @RequestMapping(path = "/")
    public Map<String, ?> index() throws Exception{

        Map<String,String> map=new HashMap<>();
        map.put("info","Welcome to webzic");

        return map;
    }


}