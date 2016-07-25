package com.logzc.webzic.reflection.type;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.Collections;

/**
 * Created by lishuang on 2016/7/25.
 */
public class TypeTest {

    @Test
    public void testType(){

        Class<?> clazz=TypeTestBean.class;

        Type type=clazz;

        System.out.println(type.getTypeName());

        Constructor<?>[] constructors = clazz.getConstructors();
        Constructor<?>[] constructors1 = clazz.getDeclaredConstructors();


        //System.out.println(constructors);
        //System.out.println(constructors1);


        Method[] methods=clazz.getDeclaredMethods();


        for (Method method:methods){

            Type[] type1 = method.getGenericParameterTypes();




            for (Type type2:type1){
                System.out.println(type2.toString());
            }


            System.out.println("------parameters------");
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter:parameters){

                System.out.println(parameter.getAnnotatedType().getType());
                Annotation[] annotations=parameter.getAnnotations();

                for (Annotation annotation:annotations){

                    System.out.println(annotation);
                }

            }

        }

    }
}
