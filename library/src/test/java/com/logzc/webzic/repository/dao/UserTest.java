package com.logzc.webzic.repository.dao;

import com.logzc.common.converter.ResolvableType;
import com.logzc.webzic.orm.field.Column;
import com.logzc.webzic.orm.table.Table;
import org.junit.Assume;
import org.junit.Test;

import java.lang.reflect.Type;

/**
 * Created by lishuang on 2016/10/11.
 */
@Table(name = "user")
public class UserTest {
    @Column(id = true)
    public Long id;
    public String username;
    public String password;
    public int gender;


    @Test
    public void testGeneric(){
        ResolvableType resolvableType = ResolvableType.forClass(UserDaoTest.class);

        //resolvableType1.getSuperType(); 得到父类
        //以下是得到接口上的
        ResolvableType[] interfaces = resolvableType.getInterfaces();
        ResolvableType inter = interfaces[0];
        ResolvableType generic = inter.getGeneric(0);
        Type type = generic.resolve();
        System.out.println(type);
        Assume.assumeTrue(type == UserTest.class);
    }
}
