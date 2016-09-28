package com.logzc.webzic.bean.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logzc.webzic.web.core.RequestParameterFinder;
import com.logzc.webzic.reflection.parameter.AsmParameterNameFinder;

/**
 * this bean factory contains all the bean needed by the framework.
 * Created by lishuang on 2016/7/26.
 */
public class WidgetBeanFactory extends AbstractBeanFactory {


    @Override
    public void init() {

        //register singletons.
        beanMap.put(ObjectMapper.class, new ObjectMapper());
        classes.add(ObjectMapper.class);

        //register ParameterNameFinder.
        beanMap.put(AsmParameterNameFinder.class, new AsmParameterNameFinder());
        classes.add(AsmParameterNameFinder.class);

        beanMap.put(RequestParameterFinder.class, new RequestParameterFinder());
        classes.add(RequestParameterFinder.class);

    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getBean(Class<T> clazz) {

        return (T) beanMap.get(clazz);

    }


}
