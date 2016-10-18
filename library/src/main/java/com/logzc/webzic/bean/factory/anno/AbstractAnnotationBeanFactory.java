package com.logzc.webzic.bean.factory.anno;

import com.logzc.webzic.bean.factory.AbstractBeanFactory;
import com.logzc.webzic.bean.processor.AutowiredBeanProcessor;
import com.logzc.webzic.bean.processor.BeanProcessor;
import com.logzc.webzic.bean.processor.RequestMappingBeanProcessor;
import com.logzc.webzic.bean.processor.ValueBeanProcessor;
import com.logzc.webzic.reflection.scanner.Scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lishuang on 2016/7/26.
 */
public abstract class AbstractAnnotationBeanFactory extends AbstractBeanFactory implements AnnotationBeanFactory {

    protected Scanner scanner;


    @Override
    public void init() throws Exception{

    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getBean(Class<T> clazz) {

        return (T) beanMap.get(clazz);
    }


}
