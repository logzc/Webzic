package com.logzc.webzic.dynamic.scanner;

import com.logzc.webzic.dynamic.fs.ZicFile;
import com.logzc.webzic.util.IOUtil;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a Annotation Scan
 * Created by lishuang on 2016/7/18.
 */
public class TypeAnnotationScanner extends Scanner {

    private static final Logger logger = LoggerFactory.getLogger(TypeAnnotationScanner.class);
    private Class<? extends Annotation> annotation;
    private List<String> classNames;

    public TypeAnnotationScanner(Class<? extends Annotation> annotationClass) {
        this.annotation = annotationClass;
        this.classNames = new ArrayList<>();
    }

    @Override
    public List<String> getClassNames() {
        return classNames;
    }

    /**
     * Judge whether the zicfile can be accepted.
     * regardless of the  difference between visible and invisible.
     */
    @Override
    public void accept(ZicFile zicFile) {

        InputStream inputStream = null;
        ClassFile classFile = null;
        try {
            inputStream = zicFile.openInputStream();
            DataInputStream dis = new DataInputStream(new BufferedInputStream(inputStream));
            classFile = new ClassFile(dis);
        } catch (IOException e) {
            return;

        } finally {
            IOUtil.close(inputStream);
        }

        AnnotationsAttribute visible = (AnnotationsAttribute) classFile.getAttribute(AnnotationsAttribute.visibleTag);
        AnnotationsAttribute invisible = (AnnotationsAttribute) classFile.getAttribute(AnnotationsAttribute.invisibleTag);

        if (visible != null) {
            for (javassist.bytecode.annotation.Annotation anno : visible.getAnnotations()) {

                if (anno.getTypeName().equals(annotation.getTypeName())) {
                    classNames.add(classFile.getName());
                }
            }
        }

        if (invisible != null) {

            for (javassist.bytecode.annotation.Annotation anno : invisible.getAnnotations()) {
                if (anno.getTypeName().equals(annotation.getTypeName())) {
                    classNames.add(classFile.getName());
                }
            }
        }

    }
}
