package com.logzc.webzic.compass.controller;

import com.logzc.webzic.annotation.RequestMapping;
import com.logzc.webzic.annotation.RestController;
import com.logzc.webzic.web.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by lishuang on 2016/7/21.
 */
@RestController
@RequestMapping(path = "/site")
public class SiteController {

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Map<String, ?> add(String siteId) {

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

    public Map<String, ?> ooh() {
        return null;
    }

}
