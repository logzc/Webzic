package com.logzc.webzic.reflection;

import com.logzc.common.util.IOUtil;
import com.logzc.webzic.exception.ZicException;
import org.objectweb.asm.ClassReader;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lishuang on 2016/7/29.
 */
public class AsmHelper {

    public static ClassReader createClassReader(Class clazz) throws ZicException {

        InputStream inputStream = null;

        try {
            ClassLoader classLoader = clazz.getClassLoader();
            inputStream = classLoader.getResourceAsStream(clazz.getName().replace(".", "/") + ".class");
            return new ClassReader(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
            throw new ZicException(e.getMessage());
        } finally {
            IOUtil.close(inputStream);
        }


    }
}
