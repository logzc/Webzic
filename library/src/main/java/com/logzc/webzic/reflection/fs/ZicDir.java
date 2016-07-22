package com.logzc.webzic.reflection.fs;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Objects;

/**
 * The abstract Dir base class of ZicFs.
 * Created by lishuang on 2016/7/17.
 */
public abstract class ZicDir {

    public abstract String getPath();

    public abstract Collection<ZicFile> getFiles();

    public static ZicDir fromURL(URL url) {

        Objects.requireNonNull(url);

        String protocol = url.getProtocol();

        if (protocol.equals("file")) {
            String path = url.getPath();
            if (path.endsWith(".jar")) {
                try {
                    URL jarUrl = new URL("jar:file:" + path + "!/");
                    return new JarInputDir(jarUrl);
                } catch (MalformedURLException e) {
                    throw new RuntimeException("convert to jar url error");
                }
            } else {
                return new SystemDir(url);
            }

        } else if (protocol.equals("jar")) {

            return new JarInputDir(url);
        } else {
            throw new RuntimeException("Not support the url protocol of " + protocol);
        }
    }
}
