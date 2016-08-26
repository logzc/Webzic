package com.logzc.webzic.orm;

import com.logzc.webzic.orm.util.OrmUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by lishuang on 2016/8/24.
 */
public class OrmUtilsTest {

    @Test
    public void codeTest() {

        String name = "a";

        String getterName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);


        System.out.println(getterName);
    }

    @Test
    public void featureTest() {

        Class<?> account = Account.class;

        Field[] fields = account.getDeclaredFields();

        for (Field field : fields) {

            Method getter = OrmUtils.findGetter(field);
            Method setter = OrmUtils.findSetter(field);

            System.out.println(field.getName() + "->" + getter);
            System.out.println(field.getName() + "->" + setter);
        }

    }

    @Test
    public void testConvert() {

        System.out.println(OrmUtils.convertCamelToUnderscore("camelCaseToSomethingElse"));

        System.out.println(OrmUtils.convertUnderScoreToCamel("w_ACT_010_LOG_DATE", true));
        System.out.println(OrmUtils.convertUnderScoreToCamel("w_ACT_010_LOG_DATE"));


    }


}
