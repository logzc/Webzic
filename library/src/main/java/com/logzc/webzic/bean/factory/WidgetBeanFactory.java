package com.logzc.webzic.bean.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logzc.webzic.converter.ConversionService;
import com.logzc.webzic.converter.DefaultConversionService;
import com.logzc.webzic.web.config.Constants;
import com.logzc.webzic.web.core.HandlerMethodManager;
import com.logzc.webzic.web.core.OutputManager;
import com.logzc.webzic.web.core.RequestParameterFinder;
import com.logzc.webzic.reflection.parameter.AsmParameterNameFinder;

/**
 * this bean factory contains all the normal bean needed by the framework.
 * Created by lishuang on 2016/7/26.
 */
public class WidgetBeanFactory extends AbstractBeanFactory {


    @Override
    public void init() {



        //register singletons.

        //JSON Mapper
        beanMap.put(ObjectMapper.class, new ObjectMapper());
        classes.add(ObjectMapper.class);

        //Constants
        beanMap.put(Constants.class,new Constants());
        classes.add(Constants.class);

        //ConversationService
        beanMap.put(ConversionService.class,new DefaultConversionService());
        classes.add(ConversionService.class);

        //HandlerMethod manager.
        beanMap.put(HandlerMethodManager.class,new HandlerMethodManager());
        classes.add(HandlerMethodManager.class);

        //Output manager
        beanMap.put(OutputManager.class,new OutputManager());
        classes.add(OutputManager.class);


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
