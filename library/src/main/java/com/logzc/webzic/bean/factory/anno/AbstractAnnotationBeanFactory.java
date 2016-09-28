package com.logzc.webzic.bean.factory.anno;

import com.logzc.webzic.bean.factory.AbstractBeanFactory;
import com.logzc.webzic.reflection.scanner.Scanner;

/**
 * Created by lishuang on 2016/7/26.
 */
public abstract class AbstractAnnotationBeanFactory extends AbstractBeanFactory implements AnnotationBeanFactory{

    protected Scanner scanner;

    @Override
    public void init(){
        //do nothing here.
    }

}
