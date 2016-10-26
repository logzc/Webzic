package com.logzc.webzic.compass.controller;

import com.logzc.webzic.annotation.RequestMapping;
import com.logzc.webzic.annotation.RestController;
import com.logzc.webzic.web.view.ViewModel;

/**
 * Created by lishuang on 2016/7/19.
 */
@RestController
public class IndexController {

    @RequestMapping(path = "/")
    public ViewModel index() throws Exception{

        ViewModel viewModel=new ViewModel();
        viewModel.setView("index.html");

        return viewModel;

    }


}