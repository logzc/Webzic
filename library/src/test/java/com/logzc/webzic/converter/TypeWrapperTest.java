package com.logzc.webzic.converter;

import com.logzc.webzic.factory.AppContext;
import com.logzc.webzic.factory.BeanFactory;
import com.logzc.webzic.web.core.MethodParameter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created by lishuang on 2016/8/22.
 */
public class TypeWrapperTest {


    @Before
    public void init(){
        //init all the annotation beans.
        AppContext.beanFactoryList.forEach(BeanFactory::init);
    }

    @Test
    public void testForField() throws Exception {

        Field addressField=SerializableTypeWrapperBean.class.getField("address");
        Type addressFieldType = TypeWrapper.forField(addressField);

        Assert.assertTrue(addressFieldType==String.class);
    }


    @Test
    public void testForMethodParameter() throws Exception{
        Method getAgeMethod=SerializableTypeWrapperBean.class.getMethod("getAge", String.class, Object.class);
        MethodParameter methodParameter=new MethodParameter(getAgeMethod,0);
        Type getAgeMethodType0= TypeWrapper.forMethodParameter(methodParameter);
        Assert.assertTrue(getAgeMethodType0==String.class);

        methodParameter=new MethodParameter(getAgeMethod,1);
        Type getAgeMethodType1= TypeWrapper.forMethodParameter(methodParameter);
        System.out.println(getAgeMethodType1);

    }


}
