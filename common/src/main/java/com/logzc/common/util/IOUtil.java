package com.logzc.common.util;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * This is the util functions for InputStream, OutputStream, Writer, Reader.
 * Created by lishuang on 2016/7/18.
 */
public class IOUtil {
    public static void close(InputStream inputStream) {
        IOUtils.closeQuietly(inputStream);
    }

    public static void close(OutputStream outputStream) {
        IOUtils.closeQuietly(outputStream);
    }

    public static void close(Reader reader) {
        IOUtils.closeQuietly(reader);
    }

    public static void close(Writer writer) {
        IOUtils.closeQuietly(writer);
    }


}
