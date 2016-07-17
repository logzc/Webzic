package com.logzc.webzic.dynamic.scanner;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * This is a Annotation Scan
 * Created by lishuang on 2016/7/18.
 */
public class TypeAnnotationScanner {

    private Annotation annotation;
    private List<Class<?>> classList;

    public TypeAnnotationScanner(Annotation annotation, List<Class<?>> classList) {
        this.annotation = annotation;
        this.classList = classList;
    }

    public Annotation getAnnotation() {
        return annotation;
    }

    public List<Class<?>> getClassList() {
        return classList;
    }
}
