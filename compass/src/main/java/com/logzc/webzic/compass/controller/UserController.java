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
@RequestMapping(path = {"/user", "/account"})
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @RequestMapping(path = "/page", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, ?> page(String name,Integer age) throws Exception{

        System.out.println("Query condition: name-> "+name+" age -> " +age);

        Map<String,List<User>> user=new HashMap<>();
        List<User> users=userDao.queryAll();
        user.put("users",users);

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