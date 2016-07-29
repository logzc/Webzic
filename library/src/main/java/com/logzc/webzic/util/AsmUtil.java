package com.logzc.webzic.util;

import org.objectweb.asm.ClassReader;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lishuang on 2016/7/29.
 */
public class AsmUtil {

    public static ClassReader createClassReader(Class clazz) throws IOException {

        InputStream inputStream = null;

        try {
            ClassLoader classLoader = clazz.getClassLoader();
            inputStream = classLoader.getResourceAsStream(clazz.getName().replace(".", "/") + ".class");
            return new ClassReader(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            IOUtil.close(inputStream);
        }


    }
}
