package com.logzc.webzic.dynamic.util;

import com.logzc.webzic.util.FileUtil;
import org.junit.Test;

import java.io.File;

/**
 * Created by lishuang on 2016/7/18.
 */
public class FileUtilTest {

    @Test
    public void testListFile(){
        System.out.println("HelloWorld");

        File dir=new File("C:/lish/Group/Logzc/Webzic/library");


        System.out.println(FileUtil.listFiles(dir,new String[]{"java"},false));
    }
}
