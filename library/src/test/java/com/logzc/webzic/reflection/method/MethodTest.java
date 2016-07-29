package com.logzc.webzic.reflection.method;

import com.logzc.webzic.reflection.parameter.AsmParameterNameFinder;
import com.logzc.webzic.reflection.parameter.ParameterNameFinder;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by lishuang on 2016/7/27.
 */
public class MethodTest {


    @Test
    public void testConstructor(){

        Class<MethodTestBean0> testBean0Class = MethodTestBean0.class;


        Constructor[] constructors=testBean0Class.getConstructors();

        System.out.println(constructors);

        Constructor[] declareContstructors=testBean0Class.getDeclaredConstructors();

        System.out.println(declareContstructors);


        Method[] methods=testBean0Class.getMethods();

        System.out.println(methods);

        Method[] declareMethods=testBean0Class.getDeclaredMethods();

        System.out.println(declareMethods);


        for (Method method:declareMethods){

            String descriptor = org.objectweb.asm.Type.getMethodDescriptor(method);
            System.out.println(method+"->"+descriptor);
        }



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

    @Test
    public void testMyAsmParameterDiscovery(){
        Class<MethodTestBean0> testBean0Class = MethodTestBean0.class;
        Method[] methods = testBean0Class.getDeclaredMethods();
        ParameterNameFinder parameterNameFinder1 =new AsmParameterNameFinder();

        for (Method method:methods){

            System.out.println(method.toString());

            List<String> xbeanParams1 = parameterNameFinder1.get(method);
            for (String str:xbeanParams1){
                System.out.println(str);
            }

            System.out.println("-----------------");
        }
        
        Constructor[] constructors = testBean0Class.getDeclaredConstructors();
        ParameterNameFinder parameterNameFinder2 =new AsmParameterNameFinder();
        for (Constructor constructor:constructors){

            System.out.println(constructor.toString());

            System.out.print("");
            List<String> xbeanParams1 = parameterNameFinder2.get(constructor);
            for (String str:xbeanParams1){
                System.out.println(str);
            }

            System.out.println("-----------------");
        }



    }
}
