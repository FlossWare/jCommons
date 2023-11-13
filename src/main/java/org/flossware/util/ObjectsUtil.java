package org.flossware.util;

/**
 *
 * Utility class in the spirit of java.util.Objects.
 *
 * @author sfloess
 *
 */
public final class ObjectsUtil {

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
        return ClassesUtil.getPackageName(java.util.Objects.requireNonNull(object, "Must have an object!").getClass());
    }

    /**
     * Default constructor not allowed.
     */
    private ObjectsUtil() {
    }
}
