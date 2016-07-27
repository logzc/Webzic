package com.logzc.webzic.reflection.method;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
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


            System.out.println("Parameter:");
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

            System.out.println("Type:");
            for (Type type:types){
                System.out.println(type);
            }

            Class<?> returnType = method.getReturnType();
            System.out.println("Return:");
            System.out.println(returnType);


            System.out.println("parameters:");
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter:parameters){
                if(parameter.isNamePresent()){
                    System.out.println(parameter.isNamePresent());
                }else{
                    System.out.println(parameter.isNamePresent());
                }

            }

            System.out.println("asm param:");
            String params[] = ParameterNameUtils.getMethodParameterNamesByAsm4(testBean0Class,method);
            for (String str:params){
                System.out.println(str);
            }

            System.out.println("-----------");



        }


    }
}
