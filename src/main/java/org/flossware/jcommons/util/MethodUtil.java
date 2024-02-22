package org.flossware.jcommons.util;

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
        return Arrays.stream(klass.getMethods()).filter(t -> t.isAnnotationPresent(annotationClass)).collect(Collectors.toCollection(ArrayList::new));
    }

    public static List<Method> findMethodsForAnnotation(final Class klass, final Annotation annotation) {
        // To do:  param valdation
        return findMethodsForAnnotationClass(klass, annotation.getClass());
    }


    public static <T extends Annotation> T findAnnotationOnMethods(final Class klass, final Class annotationClass) {
        // To do:  param validation
        
        for (final Method method : klass.getMethods()) {
            if (null != method.getAnnotation(annotationClass)) {
                return (T) method.getAnnotation(annotationClass);
            }
        }

        // TO DO:  Use streams
        //return Arrays.stream(klass.getMethods()).filter(t -> t.isAnnotationPresent(annotationClass)).collect(Collectors.toCollection(ArrayList::new));

        return null;

    }


    public static <T extends Annotation> T findAnnotationOnMethods(final Class klass, final T annotation) {
        // To do:  param validation
        return findAnnotationOnMethods(klass, annotation.getClass());
    }
}
