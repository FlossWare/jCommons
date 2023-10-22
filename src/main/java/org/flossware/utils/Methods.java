package org.flossware.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Same vein as <code>java.util.Collections</code>
 *
 * @author sfloess
 */
public class Methods {
    public static List<Method> findMethodsForAnnotationClass(final Class klass, final Class annotationClass) {
        return Arrays.stream(klass.getMethods()).filter(t -> {return t.isAnnotationPresent(annotationClass);}).collect(Collectors.toCollection(ArrayList::new));
    }

    public static List<Method> findMethodsForAnnotation(final Class klass, final Annotation annotation) {
        return findMethodsForAnnotationClass(klass, annotation.getClass());
    }

    private Methods() {
    }
}
