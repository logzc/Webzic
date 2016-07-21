package com.logzc.webzic.web.pool;

import com.logzc.webzic.dynamic.Dynamics;

/**
 * Created by lishuang on 2016/7/19.
 */
public class Pools {

    private static ControllerPool controllerPool=new ControllerPool();



    public static void init(){
        Dynamics.scan(controllerPool.getScanner());

        controllerPool.postInit();

    }


}
