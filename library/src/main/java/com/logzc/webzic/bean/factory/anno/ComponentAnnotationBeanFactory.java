package com.logzc.webzic.bean.factory.anno;

import com.logzc.webzic.annotation.Component;
import com.logzc.webzic.exception.ZicException;
import com.logzc.webzic.reflection.scanner.Scanner;
import com.logzc.webzic.reflection.scanner.TypeAnnotationScanner;

import java.util.List;

/**
 * Created by lishuang on 2016/9/28.
 */
public class ComponentAnnotationBeanFactory extends AbstractAnnotationBeanFactory {


    /**
     * when finish the necessary things. init the @Value
     */
    @Override
    public void postInit() throws Exception {
        List<String> classNames = scanner.getClassNames();

        try {
            //create the indexes of the canned controller.
            for (String className : classNames) {
                Class<?> configurationClass = this.getClassLoader().loadClass(className);
                classes.add(configurationClass);
                beanMap.put(configurationClass, configurationClass.newInstance());

            }



        } catch (Exception e) {
            throw new ZicException(e.getMessage());
        }


    }

    @Override
    public Scanner getScanner() {
        if (scanner == null) {
            scanner = new TypeAnnotationScanner(Component.class);
        }

        return scanner;
    }
}
