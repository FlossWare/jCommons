/*
 * Copyright (C) 2017 Scot P. Floess
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
package org.flossware.jcommons.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.flossware.jcommons.io.IOException;

/**
 * Properties utility class.
 *
 * @author Scot P. Floess
 */
public final class PropertyUtil {
    /**
     * Our logger.
     */
    private static final Logger LOGGER = Logger.getLogger(PropertyUtil.class.getName());


    /**
     * Return the logger.
     *
     * @return our logger.
     */
    static Logger getLogger() {
        return LOGGER;
    }

    /**
     * Default constructor not allowed.
     */
    private PropertyUtil() {
    }


    static Properties populateFromInputStream(final Properties retVal, final InputStream inputStream, final boolean closeStream) {
        try {
            retVal.load(Objects.requireNonNull(inputStream, "Must provide an input stream!"));

            return retVal;
        } catch (final Exception exception) {
            LoggerUtil.log(getLogger(), Level.WARNING, "Trouble reading input stream!", exception);

            throw new IOException(exception);
        } finally {
            if (closeStream) {
                IOUtils.close(inputStream);
            }
        }
    }

    static Properties populateFromReader(final Properties retVal, final Reader reader, final boolean closeStream) {
        try {
            retVal.load(Objects.requireNonNull(reader, "Must provide a reader!"));

            return retVal;
        } catch (final Exception exception) {
            LoggerUtil.log(getLogger(), Level.WARNING, "Trouble reading input stream!", exception);

            throw new IOException(exception);
        } finally {
            if (closeStream) {
                IOUtils.close(reader);
            }
        }
    }

    public static Properties fromInputStream(final InputStream inputStream, final boolean closeStream) {
        return populateFromInputStream(new Properties(), inputStream, closeStream);
    }

    public static Properties fromInputStream(final InputStream inputStream) {
        return fromInputStream(inputStream, false);
    }

    public static Properties fromReader(final Reader reader, final boolean closeStream) {
        return populateFromReader(new Properties(), reader, closeStream);
    }

    public static Properties fromReader(final Reader reader) {
        return PropertyUtil.fromReader(reader, false);
    }

    public static Properties createPropertiesFromFile(final File file) {
        try {
            return fromInputStream(new FileInputStream(Objects.requireNonNull(file, "Must provide a file!")), true);
        } catch (final FileNotFoundException fileNotFoundException) {
            LoggerUtil.log(getLogger(), Level.WARNING, "Trouble reading input stream!", fileNotFoundException);

            throw new IOException(fileNotFoundException);
        }
    }

    public static Properties createPropertiesFromResources(final String resource) {
        return fromInputStream(PropertyUtil.class.getResourceAsStream(resource), true);
    }
}
