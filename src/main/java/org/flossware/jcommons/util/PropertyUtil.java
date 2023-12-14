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


    static Properties populatePropertiesFromInputStream(final Properties retVal, final InputStream inputStream, final boolean closeStream) {
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

    static Properties populatePropertiesFromReader(final Properties retVal, final Reader reader, final boolean closeStream) {
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

    public static Properties createPropertiesFromInputStream(final InputStream inputStream, final boolean closeStream) {
        return populatePropertiesFromInputStream(new Properties(), inputStream, closeStream);
    }

    public static Properties createPropertiesFromInputStream(final InputStream inputStream) {
        return createPropertiesFromInputStream(inputStream, false);
    }

    public static Properties createPropertiesFromReader(final Reader reader, final boolean closeStream) {
        return populatePropertiesFromReader(new Properties(), reader, closeStream);
    }

    public static Properties createPropertiesFromReader(final Reader reader) {
        return PropertyUtil.createPropertiesFromReader(reader, false);
    }

    public static Properties createPropertiesFromFile(final File file) {
        try {
            return createPropertiesFromInputStream(new FileInputStream(Objects.requireNonNull(file, "Must provide a file!")), true);
        } catch (final FileNotFoundException fileNotFoundException) {
            LoggerUtil.log(getLogger(), Level.WARNING, "Trouble reading input stream!", fileNotFoundException);

            throw new IOException(fileNotFoundException);
        }
    }

    public static Properties createPropertiesFromResources(final String resource) {
        return createPropertiesFromInputStream(PropertyUtil.class.getResourceAsStream(resource), true);
    }
}
