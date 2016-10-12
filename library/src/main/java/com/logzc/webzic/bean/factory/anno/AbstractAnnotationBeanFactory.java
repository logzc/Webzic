package com.logzc.webzic.bean.factory.anno;

import com.logzc.webzic.bean.factory.AbstractBeanFactory;
import com.logzc.webzic.bean.processor.AutowiredBeanProcessor;
import com.logzc.webzic.bean.processor.BeanProcessor;
import com.logzc.webzic.bean.processor.RequestMappingBeanProcessor;
import com.logzc.webzic.bean.processor.ValueBeanProcessor;
import com.logzc.webzic.reflection.scanner.Scanner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lishuang on 2016/7/26.
 */
public abstract class AbstractAnnotationBeanFactory extends AbstractBeanFactory implements AnnotationBeanFactory {

    protected Scanner scanner;

    //BeanProcessors.
    protected List<BeanProcessor> beanProcessors = new ArrayList<>();

    @Override
    public void init() {
        beanProcessors.add(new ValueBeanProcessor());

        beanProcessors.add(new RequestMappingBeanProcessor());

        beanProcessors.add(new AutowiredBeanProcessor());
    }
}
