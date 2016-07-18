package com.logzc.webzic.dynamic.fs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;

/**
 * This is the implement of jar for ZicDir.
 * Created by lishuang on 2016/7/17.
 */
public class JarInputDir extends ZicDir {
    private static final Logger logger = LoggerFactory.getLogger(JarInputDir.class);
    private final URL url;

    public JarInputDir(URL url) {
        Objects.requireNonNull(url);
        String protocol = url.getProtocol();
        if(protocol.equals("jar")){
            this.url = url;
        }else{
            throw new RuntimeException("url should be a jar.");
        }

    }

    @Override
    public String getPath() {
        return url.getPath();
    }


    //When jar is larger than 2G. This method may produce JDK-6916399 bug. We just ignore it.
    //http://bugs.java.com/view_bug.do?bug_id=6916399
    @Override
    public Collection<ZicFile> getFiles() {

        try {
            JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
            if (jarURLConnection != null) {
                JarFile jarFile = jarURLConnection.getJarFile();
                if (jarFile != null) {
                    Enumeration<JarEntry> jarEntries = jarFile.entries();
                    Collection<ZicFile> jarInputFiles=new ArrayList<>();

                    while (jarEntries.hasMoreElements()) {
                        JarEntry jarEntry = jarEntries.nextElement();

                        if (!jarEntry.isDirectory()){
                            jarInputFiles.add(new JarInputFile(JarInputDir.this,jarFile,jarEntry));
                        }
                    }
                    return jarInputFiles;
                }
            }
        }catch (IOException e){
            logger.debug("could not open getFiles",e);
            return Collections.emptyList();
        }

        logger.debug("could not openConnection when getFiles.");
        return Collections.emptyList();
    }



}
