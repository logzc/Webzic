package com.logzc.webzic.generic;

import com.logzc.webzic.converter.ResolvableType;
import com.logzc.webzic.generic.model.*;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 13-12-18
 * <p>Version: 1.0
 */

public class GenricInjectTest {

    private Service<A, B> abService;

    private Service<C, D> cdService;

    private List<List<String>> list;

    private Map<String, Map<String, Integer>> map;

    private List<String>[] array;

    public GenricInjectTest() {
    }


    @Test
    public void test() {
        Assert.assertTrue(abService instanceof ABService);
        Assert.assertTrue(cdService instanceof CDService);
    }

    @Test
    public void testGenericAPI() {

//        ParameterizedType parameterizedType = (ParameterizedType) ABService.class.getGenericInterfaces()[0];
//        Type genericType = parameterizedType.getActualTypeArguments()[1];

        //得到类型上的泛型   如果你的类可能被代理 可以通过ClassUtils.getUserClass(ABService.class)得到原有的类型
        ResolvableType resolvableType = ResolvableType.forClass(ABService.class);

        //resolvableType1.getSuperType(); 得到父类
        //以下是得到接口上的
        ResolvableType[] interfaces = resolvableType.getInterfaces();
        ResolvableType inter = interfaces[0];
        ResolvableType generic = inter.getGeneric(1);
        Type type = generic.resolve();
        System.out.println(type);
        Assume.assumeTrue(type == B.class);

        //转换为某个类型（父类/实现的接口）  还提供了简便方法 asMap() /asCollection
        resolvableType = ResolvableType.forClass(ABService.class);
        resolvableType = resolvableType.as(Service.class);
        generic = resolvableType.getGeneric(1);
        type = generic.resolve();

        System.out.println(type);
        //这里表明依旧持有泛型类型。
        Assume.assumeTrue(type == B.class);


        resolvableType = ResolvableType.forClass(Service.class);;
        generic = resolvableType.getGeneric(1);
        type = generic.resolve();

        System.out.println(type);
        //此处表明泛型已经弱化为了Object.
        Assume.assumeTrue(type == Object.class);

        /*
        ///得到字段的
        ResolvableType resolvableType2 =
                ResolvableType.forField(ReflectionUtil.findField(GenricInjectTest.class, "cdService"));
        System.out.println(resolvableType2.getGeneric(0).resolve()); //得到某个位置的 如<C, D>  0就是C 1就是D

        //嵌套的
        ResolvableType resolvableType3 =
                ResolvableType.forField(ReflectionUtil.findField(GenricInjectTest.class, "list"));
        System.out.println(resolvableType3.getGeneric(0).getGeneric(0).resolve());

        //Map嵌套
        ResolvableType resolvableType4 =
                ResolvableType.forField(ReflectionUtil.findField(GenricInjectTest.class, "map"));
        System.out.println(resolvableType4.getGeneric(1).getGeneric(1).resolve());
        System.out.println(resolvableType4.getGeneric(1, 1).resolve());

        //方法返回值
        ResolvableType resolvableType5 =
                ResolvableType.forMethodReturnType(ReflectionUtil.findMethod(GenricInjectTest.class, "method"));
        System.out.println(resolvableType5.getGeneric(1, 0).resolve());

        //构造器参数
        ResolvableType resolvableType6 =
                ResolvableType.forConstructorParameter(ClassUtils.getConstructorIfAvailable(Const.class, List.class, Map.class), 1);
        System.out.println(resolvableType6.getGeneric(1, 0).resolve());

        //数组
        ResolvableType resolvableType7 =
                ResolvableType.forField(ReflectionUtils.findField(GenricInjectTest.class, "array"));
        System.out.println(resolvableType7.isArray());
        System.out.println(resolvableType7.getComponentType().getGeneric(0).resolve());

        //自定义一个泛型数组 List<String>[]
        ResolvableType resolvableType8 = ResolvableType.forClassWithGenerics(List.class, String.class);
        ResolvableType resolvableType9 = ResolvableType.forArrayComponent(resolvableType8);
        System.out.println(resolvableType9.getComponentType().getGeneric(0).resolve());

        //比较两个泛型是否可以赋值成功
        System.out.println(resolvableType7.isAssignableFrom(resolvableType9));

        ResolvableType resolvableType10 = ResolvableType.forClassWithGenerics(List.class, Integer.class);
        ResolvableType resolvableType11 = ResolvableType.forArrayComponent(resolvableType10);
        System.out.println(resolvableType11.getComponentType().getGeneric(0).resolve());

        System.out.println(resolvableType7.isAssignableFrom(resolvableType11));

*/

    }

    private HashMap<String, List<String>> method() {
        return null;
    }

    static class Const {
        public Const(List<List<String>> list, Map<String, Map<String, Integer>> map) {
        }

    }

}

