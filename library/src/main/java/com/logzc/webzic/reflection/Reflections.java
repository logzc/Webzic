package com.logzc.webzic.reflection;

import com.logzc.webzic.bean.AppContext;
import com.logzc.webzic.reflection.fs.ZicDir;
import com.logzc.webzic.reflection.fs.ZicFile;
import com.logzc.webzic.reflection.scanner.Scanner;
import com.logzc.common.util.Assert;
import com.logzc.webzic.web.controller.WebzicPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.*;

/**
 * This the entrance of dynamic reflection.
 * Created by lishuang on 2016/7/18.
 */
public class Reflections {

    private static final Logger logger = LoggerFactory.getLogger(Reflections.class);

    public static void scan(List<Scanner> scanners) {

        Assert.notEmpty(scanners);

        Scanner[] array = new Scanner[scanners.size()];
        scanners.toArray(array);

        scan(array);
    }

    /**
     * Scan one time to fill the results into scan's list.
     */
    public static void scan(Scanner... scanners) {

        Assert.notEmpty(scanners);

        Collection<URL> urls;
        String scanPackage= AppContext.getConstants().getScanPackage();
        if (scanPackage == null) {
            urls = ClassPaths.forClassLoader();
        } else {
            String frameworkPackage = WebzicPath.class.getPackage().getName();
            Collection<URL> frameworkUrls = ClassPaths.forPackage(frameworkPackage);

            Collection<URL> specifyUrls = ClassPaths.forPackage(scanPackage);

            urls = new HashSet<>();
            urls.addAll(frameworkUrls);
            urls.addAll(specifyUrls);
        }

        //Collection<URL> urls= ClassPaths.forPackage("com.logzc.webzic.compass");

        //These three urls have different results.
        //Collection<URL> urls = ClassPaths.forJavaClassPath();
        //Collection<URL> urls=ClassPaths.forPackage("");

        for (URL url : urls) {
            //logger.debug("scan...");
            logger.debug("scaning..." + url.toString());

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
