package com.logzc.webzic.factory.anno;

import com.logzc.webzic.factory.BeanFactory;
import com.logzc.webzic.reflection.scanner.Scanner;
import com.logzc.webzic.util.ClassUtil;

/**
 * Created by lishuang on 2016/7/19.
 */
public interface AnnotationBeanFactory extends BeanFactory {
    void postInit();

    Scanner getScanner();

}
