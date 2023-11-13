/*
 * Copyright (C) 2015 sfloess
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.flossware.util;

import java.util.Objects;

/**
 * Utility class for class functionality.
 *
 * @author sfloess
 */
public final class ClassesUtil {

    /**
     * Compute the package for klass.
     *
     * @param klass the class for whom we desire a package.
     *
     * @return the package for klass.
     */
    public static String getPackageName(final Class klass) {
        return Objects.requireNonNull(klass, "Must have a class!").getPackage().getName();
    }

    /**
     * Return the class for object.
     *
     * @param <T>    the type of class.
     * @param object the object for whom we desire a class.
     *
     * @return the class for object.
     */
    public static <T> Class<T> getClass(final Object object) {
        return (Class<T>) Objects.requireNonNull(object, "Must have an object!").getClass();
    }

    /**
     * Default constructor not allowed.
     */
    private ClassesUtil() {
    }
}
