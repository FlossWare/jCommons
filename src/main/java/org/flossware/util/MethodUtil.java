package org.flossware.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Same vein as <code>java.util.Collections</code>
 *
 * @author sfloess
 */
public class MethodUtil {
    /**
     * Default minimum size for an array.
     */
    public static final int DEFAULT_MIN_ARRAY_LENGTH = 1;

    /**
     * Our logger.
     */
    private static final Logger logger = Logger.getLogger(MethodUtil.class.getName());

    /**
     * Return the logger.
     */
    private static Logger getLogger() {
        return logger;
    }

    private MethodUtil() {
    }
    
    public static List<Method> findMethodsForAnnotationClass(final Class klass, final Class annotationClass) {
        // To do:  param validation
        return Arrays.stream(klass.getMethods()).filter(t -> {return t.isAnnotationPresent(annotationClass);}).collect(Collectors.toCollection(ArrayList::new));
    }

    public static List<Method> findMethodsForAnnotation(final Class klass, final Annotation annotation) {
        // To do:  param valdation
        return findMethodsForAnnotationClass(klass, annotation.getClass());
    }
}
