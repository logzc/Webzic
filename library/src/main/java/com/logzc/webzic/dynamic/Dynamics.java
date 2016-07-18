package com.logzc.webzic.dynamic;

import com.logzc.webzic.dynamic.fs.ZicDir;
import com.logzc.webzic.dynamic.fs.ZicFile;
import com.logzc.webzic.dynamic.scanner.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Collection;

/**
 * This the entrance of dynamic reflection.
 * Created by lishuang on 2016/7/18.
 */
public class Dynamics {


    private static final Logger logger = LoggerFactory.getLogger(Dynamics.class);

    /**
     * Scan one time to fill the results into scan's list.
     */
    public static void scan(Scanner... scanners) {

        if (scanners != null && scanners.length > 0) {

            Collection<URL> urls = ClassPaths.forJavaClassPath();

            for (URL url : urls) {
                //logger.debug("scan...");
                logger.debug("scaning..."+url.toString());

                ZicDir zicDir = ZicDir.fromURL(url);

                Collection<ZicFile> zicFiles = zicDir.getFiles();

                for (ZicFile file : zicFiles) {

                    for (Scanner scanner : scanners) {

                        scanner.accept(file);


                    }

                }

            }
        }

    }

}
