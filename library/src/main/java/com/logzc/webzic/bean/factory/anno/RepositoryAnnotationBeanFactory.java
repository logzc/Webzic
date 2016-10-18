package com.logzc.webzic.bean.factory.anno;

import com.logzc.webzic.annotation.Repository;
import com.logzc.webzic.annotation.RestController;
import com.logzc.webzic.bean.AppContext;
import com.logzc.webzic.bean.processor.BeanProcessor;
import com.logzc.webzic.converter.ResolvableType;
import com.logzc.webzic.orm.dao.DaoManager;
import com.logzc.webzic.orm.support.ConnectionSource;
import com.logzc.webzic.reflection.scanner.Scanner;
import com.logzc.webzic.reflection.scanner.TypeAnnotationScanner;
import com.logzc.webzic.web.controller.ExceptionController;
import com.logzc.webzic.web.controller.WebzicPath;
import com.logzc.webzic.web.core.HandlerMethod;
import com.logzc.webzic.web.core.HandlerMethodManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * This is a pool containers all the @Controller.
 * Created by lishuang on 2016/7/19.
 */
public class RepositoryAnnotationBeanFactory extends AbstractAnnotationBeanFactory {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * when finish the necessary things. init the request mapping.
     */
    @Override
    public void postInit() throws Exception {


        //find all the methods with @RequestMapping annotation.
        List<String> classNames = scanner.getClassNames();

        ConnectionSource connectionSource=AppContext.getConnectionSource();

        //create the indexes of the scanned repository.
        //@Repository are all interfaces, we have to instance it.
        for (String className : classNames) {
            Class<?> repositoryClass = this.getClassLoader().loadClass(className);

            classes.add(repositoryClass);


            ResolvableType resolvableType = ResolvableType.forClass(repositoryClass);

            //get Dao<D,ID>
            ResolvableType[] interfaces = resolvableType.getInterfaces();
            ResolvableType inter = interfaces[0];
            ResolvableType generic = inter.getGeneric(0);
            Class<?> tableClazz = generic.resolve();

            //Instance the interfaces
            Object repository= DaoManager.newInstance(connectionSource,repositoryClass,tableClazz);

            beanMap.put(repositoryClass, repository);
        }

    }


    @Override
    public Scanner getScanner() {

        if (scanner == null) {
            scanner = new TypeAnnotationScanner(Repository.class);
        }

        return scanner;
    }


}
