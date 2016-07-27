package com.logzc.webzic.compass.controller;

import com.logzc.webzic.annotation.Autowired;
import com.logzc.webzic.annotation.RequestMapping;
import com.logzc.webzic.annotation.RestController;
import com.logzc.webzic.compass.service.UserService;
import com.logzc.webzic.web.core.RequestMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lishuang on 2016/7/19.
 */
@RestController
@RequestMapping(path = {"/user", "/account"})
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/page", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, ?> page() {

        Map<String,String> user=new HashMap<>();
        user.put("name","lishuang");
        user.put("age","18");
        return user;
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Map<String, ?> add() {

        return null;
    }

    @RequestMapping(path = "/del", method = RequestMethod.DELETE)
    public Map<String, ?> del() {

        return null;
    }

    @RequestMapping(path = "/edit", method = RequestMethod.POST)
    public Map<String, ?> edit() {

        return null;
    }

    @RequestMapping(path = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, ?> list() {

        return null;
    }

    @RequestMapping(path = "/detail")
    public Map<String, ?> detail() {

        return null;
    }


}