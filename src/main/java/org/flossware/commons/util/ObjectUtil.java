package org.flossware.commons.util;

import java.util.logging.Logger;

/**
 *
 * Utility class in the spirit of java.util.Objects.
 *
 * @author sfloess
 *
 */
public final class ObjectUtil {
    /**
     * Default minimum size for an array.
     */
    public static final int DEFAULT_MIN_ARRAY_LENGTH = 1;

    /**
     * Our logger.
     */
    private static final Logger logger = Logger.getLogger(ObjectUtil.class.getName());

    /**
     * Return the logger.
     */
    private static Logger getLogger() {
        return logger;
    }

    /**
     * Default error message when a parameter is bad.
     */
    public static final String DEFAULT_ERROR_MSG = "Invalid value";

    /**
     * Compute the package for object.
     *
     * @param object the object for whom we desire a package.
     *
     * @return the package for object.
     */
    public static String getPackage(final Object object) {
        return ClassUtil.getPackageName(java.util.Objects.requireNonNull(object, "Must have an object!").getClass());
    }

    /**
     * Default constructor not allowed.
     */
    private ObjectUtil() {
    }
}
