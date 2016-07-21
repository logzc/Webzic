package com.logzc.webzic.demo.controller;

import com.logzc.webzic.annotation.Autowired;
import com.logzc.webzic.annotation.RequestMapping;
import com.logzc.webzic.web.annotation.RequestMethod;
import com.logzc.webzic.annotation.RestController;
import com.logzc.webzic.demo.service.UserService;

import java.util.Map;

/**
 * This is the user controller.
 * Created by lishuang on 2016/7/12.
 */
@RestController
@RequestMapping(path = {"/user", "/account"})
public class UserController {

    @Autowired
    private UserService userService;

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
