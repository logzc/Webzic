package com.lish.fork.javassist;

import javassist.*;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.annotation.Annotation;
import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.net.URL;

/**
 * Created by lishuang on 2016/7/15.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        //createAClass();
        scanAnnotation();
        //useAOP();
    }


    //演示如何使用javassit来动态创建一个类
    public static void createAClass() throws Exception {
        ClassPool cp = ClassPool.getDefault();
        CtClass ctClass = cp.makeClass("com.lish.fork.javaassist.Target");

        StringBuffer body = null;


        CtField ctField = new CtField(cp.get(String.class.getName()), "name", ctClass);
        ctField.setModifiers(Modifier.PRIVATE);

        ctClass.addMethod(CtNewMethod.setter("setName", ctField));
        ctClass.addMethod(CtNewMethod.getter("getName", ctField));
        ctClass.addField(ctField, CtField.Initializer.constant("default"));

        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{}, ctClass);
        body = new StringBuffer();
        body.append("{\n name=\"logzc\";\n}");
        ctConstructor.setBody(body.toString());
        ctClass.addConstructor(ctConstructor);

        CtMethod ctMethod = new CtMethod(CtClass.voidType, "hello", new CtClass[]{}, ctClass);
        ctMethod.setModifiers(Modifier.PUBLIC);
        body = new StringBuffer();
        body.append("{\n System.out.println(name);}");
        ctMethod.setBody(body.toString());
        ctClass.addMethod(ctMethod);

        Class<?> c = ctClass.toClass();
        Object o = c.newInstance();
        Method method = o.getClass().getMethod("hello");
        method.invoke(o);
    }


    //演示了如何使用javassit来实现AOP功能
    public static void useAOP() throws Exception {
        ProxyFactory factory = new ProxyFactory();
        //设置父类，ProxyFactory将会动态生成一个类，继承改父类
        factory.setSuperclass(TargetClass.class);
        //设置过滤器，判断哪些方法需要被拦截
        factory.setFilter(new MethodFilter() {
            @Override
            public boolean isHandled(Method m) {


                return m.getName().equals("hello");
            }
        });

        //设置拦截处理
        factory.setHandler(new MethodHandler() {
            @Override
            public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
                //拦截后前置处理，改写name属性的内容
                TargetClass targetClass = (TargetClass) self;
                System.out.println("Before Hello");
                Object nowObj = proceed.invoke(self, args);
                System.out.println("After Hello");
                return nowObj;

            }
        });

        Class<?> c = factory.createClass();
        TargetClass targetClass = (TargetClass) c.newInstance();
        targetClass.hello();


    }

    //使用javassit来扫描带有某个annotation的类
    //这个实例演示了在没有加载某个class的情况下就已经获取到了其Annotation信息。
    public static void scanAnnotation() throws Exception {

        URL url = Main.class.getClassLoader().getResource("com/lish/fork/javassist/TestAnno.class");
        if (url == null) {
            return;
        }
        FileInputStream fileInputStream = new FileInputStream(url.getPath());
        DataInputStream dataInputStream = new DataInputStream(fileInputStream);

        ClassFile classFile = new ClassFile(dataInputStream);
        String className = classFile.getName();
        System.out.println("ClassName:"+className);
        AnnotationsAttribute visible = (AnnotationsAttribute) classFile.getAttribute(AnnotationsAttribute.visibleTag);
        AnnotationsAttribute invisible = (AnnotationsAttribute) classFile.getAttribute(AnnotationsAttribute.invisibleTag);

        for (Annotation annotation : visible.getAnnotations()) {
            System.out.println(annotation.getTypeName());
        }
        System.out.println("------------------");
        for (Annotation annotation : invisible.getAnnotations()) {
            System.out.println(annotation.getTypeName());
        }

    }
}
