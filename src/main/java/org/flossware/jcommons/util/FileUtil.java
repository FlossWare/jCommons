/*
 * Copyright (C) 2016 Scot P. Floess
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
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.flossware.jcommons.io.FileException;

/**
 * A file utility class.
 *
 * @author Scot P. Floess
 */
public final class FileUtil {
    /**
     * Our logger.
     */
    private static final Logger LOGGER = Logger.getLogger(FileUtil.class.getName());

    /**
     * Default constructor not allowed.
     */
    private FileUtil() {
    }

    /**
     * Return the logger.
     *
     * @return our logger.
     */
    static Logger getLogger() {
        return LOGGER;
    }

    /**
     * Return a file input stream for file.
     *
     * @param file the file for whom we desire a file input stream.
     *
     * @return a file input stream.
     *
     * @throws IllegalArgumentException if file is null.
     * @throws FileException            if there is any problem creating the file input stream.
     */
    public static FileInputStream getFileInputStream(final File file) {
        try {
            return new FileInputStream(Objects.requireNonNull(file, "Cannot have a null file!"));
        } catch (final FileNotFoundException fileNotFoundException) {
            LoggerUtil.log(getLogger(), Level.WARNING, fileNotFoundException, "File [{0}] not found", file);
            throw new FileException(fileNotFoundException);
        }
    }

    /**
     * Return a file input stream for file.
     *
     * @param fileName the name of the file for whom we desire a file input stream.
     *
     * @return a file input stream.
     *
     * @throws IllegalArgumentException if fileName is null or empty.
     * @throws FileException            if there is any problem creating the file input stream.
     */
    public static FileInputStream getFileInputStream(final String fileName) {
        return getFileInputStream(new File(StringUtil.ensureString(fileName, "Cannot have a null or empty file name!")));
    }

    /**
     * Ensures a file exists.
     *
     * @param file the file to determine if it exists.
     *
     * @return file if it exists.
     *
     * @throws IllegalArgumentException if file does not exist.
     */
    public static File ensureFileExists(final File file) {
        Objects.requireNonNull(file, "Cannot ensure a null file!");

        if (!file.exists()) {
            throw new IllegalArgumentException(file + " does not exist!");
        }

        return file;
    }

    /**
     * Ensures a file exists.
     *
     * @param file the file to determine if it exists.
     *
     * @return file if it exists.
     *
     * @throws IllegalArgumentException if file does not exist.
     */
    public static File ensureFileExists(final String file) {
        return ensureFileExists(new File(StringUtil.ensureString(file, "Cannot ensure an empty file name!")));
    }
}
