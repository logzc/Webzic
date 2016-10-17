package com.logzc.webzic.bean.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lishuang on 2016/10/17.
 */
public class BeanProcessorManager {

    //BeanProcessors.
    protected List<BeanProcessor> beanProcessors = new ArrayList<>();

    public BeanProcessorManager() {


        beanProcessors.add(new AutowiredBeanProcessor());
        beanProcessors.add(new RepositoryBeanProcessor());
        beanProcessors.add(new RequestMappingBeanProcessor());
        beanProcessors.add(new ValueBeanProcessor());


    }


    public void process(Map<Class<?>, Object> beanMap) throws Exception {
        //process the bean processors.
        for (BeanProcessor beanProcessor : beanProcessors) {

            for (Map.Entry<Class<?>, Object> entry : beanMap.entrySet()) {

                beanProcessor.process(entry.getValue(), entry.getKey());

            }
        }
    }

}
