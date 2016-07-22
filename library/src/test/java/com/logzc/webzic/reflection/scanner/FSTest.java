package com.logzc.webzic.reflection.scanner;

import com.logzc.webzic.reflection.fs.ZicDir;
import com.logzc.webzic.reflection.fs.ZicFile;
import org.junit.Test;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by lishuang on 2016/7/18.
 */
public class FSTest {

    @Test
    public void testFromeURL() throws Exception {


        URL url = new URL("file:/C:/lish/Group/Logzc/Webzic/demo/libs/fork-1.0.jar");

        ZicDir dir = ZicDir.fromURL(url);

        for (ZicFile file : dir.getFiles()) {
            System.out.println(file.getRelativePath());
        }
    }

    @Test
    public void testListFiles() throws Exception {

        Files.walk(Paths.get("C:/lish/Group/Logzc/Webzic/fork/src/main")).forEach(filePath -> {
            if (Files.isRegularFile(filePath)) {
                System.out.println(filePath);
            }
        });
    }
}
