package com.logzc.webzic.reflection.method;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created by lishuang on 2016/7/27.
 */
public class MethodTest {


    @Test
    public void testBeans(){
        MethodTestBean0 methodTestBean0=new MethodTestBean0();
        methodTestBean0.add("good","morning");
    }

    @Test
    public void testMethod() {

        Class<MethodTestBean0> testBean0Class = MethodTestBean0.class;


        Method[] methods = testBean0Class.getDeclaredMethods();


        for (Method method : methods) {
            System.out.println(method);


            Annotation[][] annotations = method.getParameterAnnotations();


            Class<?>[] parameterTypes = method.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                Class<?> parameterType=parameterTypes[i];

                Annotation[] annos=annotations[i];

                for (Annotation annotation:annos){
                    System.out.println(annotation);
                }


                System.out.println(parameterType.getName());
            }


            Type[] types = method.getGenericParameterTypes();

            for (Type type:types){
                System.out.println(type);
            }

            System.out.println("-----------");



        }


    }
}
