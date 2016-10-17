package com.logzc.webzic.bean.processor;

import com.logzc.webzic.annotation.Value;
import com.logzc.webzic.bean.AppContext;
import com.logzc.webzic.converter.TypeDescriptor;
import com.logzc.webzic.util.PropertyUtil;

import java.lang.reflect.Field;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * process @Value annotation for beans.
 * Created by lishuang on 2016/9/28.
 */
public class ValueBeanProcessor implements BeanProcessor {

    private static Properties properties = null;

    @Override
    public void process(Object bean, Class<?> clazz) throws Exception {
        if (properties == null) {
            properties = PropertyUtil.loadProperties("/config.properties");
        }

        //find all the fields with @Value.
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {

            if (field.isAnnotationPresent(Value.class)) {

                Value value = field.getAnnotation(Value.class);

                String template = value.value();

                //start with "${" and end with "}"
                Pattern pattern = Pattern.compile("^\\$\\{(.*?)\\}$");
                Matcher matcher = pattern.matcher(template);

                String val = null;
                if (matcher.find()) {
                    String propertyKey = matcher.group(1);

                    val = properties.getProperty(propertyKey);

                } else {
                    val = template;
                }

                field.setAccessible(true);

                Object result = AppContext.getConversionService().convert(val, TypeDescriptor.valueOf(String.class), TypeDescriptor.forField(field));
                field.set(bean, result);

            }
        }
    }

    @Override
    public void beforeInit(Object bean, Class<?> clazz) throws Exception {



    }
    @Override
    public void afterInit(Object bean, Class<?> clazz) throws Exception {



    }
}
