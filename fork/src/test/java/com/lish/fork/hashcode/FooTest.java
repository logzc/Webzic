package com.lish.fork.hashcode;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lishuang on 2016/7/19.
 */
public class FooTest {


    @Test
    public void testHashCode(){

        Foo foo1=new Foo("lishuang","shanghai");
        foo1.hobbies.add("tennis");
        foo1.hobbies.add("swimming");
        Foo foo2=new Foo("lishuang","shanghai");
        foo2.hobbies.add("swimming");

        System.out.println("foo1 hashcode:"+foo1.hashCode());
        System.out.println("foo2 hashcode:"+foo2.hashCode());
        System.out.println(foo1.equals(foo2));



        Set<Foo> fooSet=new HashSet<>();
        fooSet.add(foo1);
        fooSet.add(foo2);

        System.out.println(fooSet);
    }


    public enum Bar{
        GET,
        POST,
        PUT,
        DELETE
    }

    @Test
    public void testSetHashCode(){
        Set<Bar> set1=new HashSet<>();
        set1.add(Bar.GET);
        set1.add(Bar.DELETE);
        Set<Bar> set2=new HashSet<>();
        set2.add(Bar.DELETE);
        set2.add(Bar.GET);
        set2.add(Bar.DELETE);

        System.out.println(set1.hashCode());
        System.out.println(set2.hashCode());
        System.out.println(set1.equals(set2));
        System.out.println(set1==set2);

    }
}
