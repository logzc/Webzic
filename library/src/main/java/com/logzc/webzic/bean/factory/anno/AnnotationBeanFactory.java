package com.logzc.webzic.bean.factory.anno;

import com.logzc.webzic.bean.factory.BeanFactory;
import com.logzc.webzic.reflection.scanner.Scanner;

/**
 * Created by lishuang on 2016/7/19.
 */
public interface AnnotationBeanFactory extends BeanFactory {
    void postInit();

    Scanner getScanner();

}
