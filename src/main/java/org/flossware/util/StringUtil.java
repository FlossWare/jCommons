package org.flossware.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.commons.lang3.StringUtils;

/**
 * Same vein as <code>java.util.Objects</code>
 *
 * @author sfloess
 */
public class StringUtil {
    /**
     * Our logger.
     */
    private static final Logger logger = Logger.getLogger(StringUtil.class.getName());

    /**
     * Return the logger.
     */
    private static Logger getLogger() {
        return logger;
    }

    public static final String STRING_CANNOT_BE_BLANK = "String cannot be blank!";

    /**
     * Default separator.
     */
    public static final String DEFAULT_SEPARATOR = "";

    public static String requireNonBlank(final String string, final String message) {
        if (StringUtils.isBlank(string)) {
            LoggerUtil.log(getLogger(), Level.SEVERE, "String is empty [", string, "]");

            throw new IllegalArgumentException(message);
        }

        return string;
    }

    public static String requireNonBlank(final String string) {
        return requireNonBlank(string, STRING_CANNOT_BE_BLANK);
    }


    /**
     * URL encodes <code>str</code>.
     */
    public static String asUrlEncoded(final String str) {
        try {
            return URLEncoder.encode(str, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            LoggerUtil.log(getLogger(), Level.SEVERE, ex, "Trouble encoding [", str, "]");

            throw new IllegalArgumentException("Trouble URL encoding [" + str + "]", ex);
        }
    }


    /**
     * Checks <code>str</code> to ensureObject it is not null nor empty.
     *
     * @param str The string to inspect to ensureObject its not null nor empty.
     * @param errorMsg The error message within the raised exception if
     * <code>str</code> is null or empty.
     *
     * @return str if it is not null or empty.
     *
     * @throws IllegalArgumentException if <code>object</code> is null.
     */
    public static String ensureString(final String str, final String errorMsg) throws IllegalArgumentException {
        if (StringUtils.isBlank(str)) {
            throw new IllegalArgumentException(errorMsg);
        }

        return str;
    }

    /**
     * Checks <code>str</code> to ensureObject it is not null nor empty.
     *
     * @param str The string to inspect to ensureObject its not null nor empty.
     *
     * @return str if it is not null or empty.
     *
     * @throws IllegalArgumentException if <code>object</code> is null.
     */
    public static String ensureString(final String str) throws IllegalArgumentException {
        return ensureString(str, ObjectUtil.DEFAULT_ERROR_MSG);
    }

    /**
     * Return true if a separator can be appended or false if not. To append,
     * the index must be less than or equal to the array's length - 2.
     *
     * @param index the place within the array we are processing.
     * @param objs the array of objects being processed.
     *
     * @return true if we can append a separator or false if not.
     */
    static boolean isSeparatorAppendable(final String separator, final int index, final Object... objs) {
        return LoggerUtil.logAndReturn(getLogger(), Level.FINEST, "Is the separator appendable [{0}] for index [{1}]", null != objs && index <= (objs.length - 2) && !objs[index].toString().endsWith(separator), index);
    }

    /**
     * Concat objects together in <code>stringBuilder</code> using the toString
     * of the <code>objs</code>.
     *
     * @param stringBuilder the string builder form whom we will concatenate.
     * @param isSeparatorAtEnd if true denotes we will always have the separator
     * appended.
     * @param separator the separator to use between concatenation.
     * @param objs the objects to concatenate.
     *
     * @return the string builder with concatendated data.
     */
    public static StringBuilder concatWithSeparator(final StringBuilder stringBuilder, final boolean isSeparatorAtEnd, final String separator, Object... objs) {
        ArrayUtil.ensureArray(objs, "Must have a list of objects to concat!");

        for (int index = 0; index < objs.length; index++) {
            stringBuilder.append(objs[index]);

            if (isSeparatorAppendable(separator, index, objs)) {
                stringBuilder.append(separator);
            }
        }

        if (isSeparatorAtEnd) {
            stringBuilder.append(separator);
        }

        LoggerUtil.log(getLogger(), Level.FINEST, "Returning [{0}]", stringBuilder);

        return stringBuilder;
    }

    /**
     * Concat objects together and return the toString of the concatenation.
     *
     * @param isSeparatorAtEnd if true denotes we will always have the separator
     * appended.
     * @param separator the separator to use between concatenation.
     * @param objs the objects to concatenate.
     *
     * @return the string representation of the concatenation.
     */
    public static String concatWithSeparator(final boolean isSeparatorAtEnd, final String separator, Object... objs) {
        return concatWithSeparator(new StringBuilder(), isSeparatorAtEnd, separator, objs).toString();
    }

    /**
     * Concat objects together in <code>stringBuilder</code> using the toString
     * of the <code>objs</code>.
     *
     * @param stringBuilder the string builder form whom we will concatenate.
     * @param separator the separator to use between concatenation.
     * @param objs the objects to concatenate.
     *
     * @return the string representation of the concatenation.
     */
    public static StringBuilder concatWithSeparator(final StringBuilder stringBuilder, final String separator, Object... objs) {
        return concatWithSeparator(stringBuilder, false, separator, objs);
    }

    /**
     * Concat objects together and return the toString of the concatenation.
     *
     * @param separator the separator to use between concatenation.
     * @param objs the objects to concatenate.
     *
     * @return the string representation of the concatenation.
     */
    public static String concatWithSeparator(final String separator, Object... objs) {
        return concatWithSeparator(false, separator, objs);
    }

    /**
     * Concat objects together in <code>stringBuilder</code> using the toString
     * of the <code>objs</code>.
     *
     * @param stringBuilder the string builder form whom we will concatenate.
     * @param objs the objects to concatenate.
     *
     * @return the string representation of the concatenation.
     */
    public static StringBuilder concat(final StringBuilder stringBuilder, Object... objs) {
        return concatWithSeparator(stringBuilder, DEFAULT_SEPARATOR, objs);
    }

    /**
     * Concat objects together and return the toString of the concatenation.
     *
     * @param objs the objects to concatenate.
     *
     * @return the string representation of the concatenation.
     */
    public static String concat(Object... objs) {
        return concatWithSeparator(DEFAULT_SEPARATOR, objs);
    }

    /**
     * Return true if <code>str</code> contains <code>contains</code>.
     *
     * @param str is the string to examine for containing <code>contains</code>.
     * @param contains is the string to see if <code>str</code> is contained.
     *
     * @return true if <code>str</code> contains <code>contains</code>.
     */
    public static boolean isContained(final String str, final String contains) {
        final boolean retVal = (null == str) ? false : str.contains(contains);

        LoggerUtil.log(getLogger(), Level.FINEST, "Exception messsage contained result [{0}] for [{1}] message [{2}]", retVal, contains, str);

        return retVal;
    }

    static void toStream(final OutputStream os, final Serializable serializable) {
        if (null == serializable) {
            LoggerUtil.log(getLogger(), Level.WARNING, "Cannot serialize a null object!");

            return;
        }

        try (final ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(serializable);
        } catch (final IOException ioException) {
            LoggerUtil.log(getLogger(), Level.SEVERE, ioException, "Trouble serializing object as a string!");
        }
    }

    static void toCompressedStream(final ByteArrayOutputStream baos, final Serializable serializable) {
        if (null == serializable) {
            LoggerUtil.log(getLogger(), Level.WARNING, "Cannot serialize a null object!");

            return;
        }

        try (final GZIPOutputStream gos = new GZIPOutputStream(baos)) {
            toStream(gos, serializable);
        } catch (final IOException ioException) {
            LoggerUtil.log(getLogger(), Level.SEVERE, ioException, "Trouble serializing object as a string!");
        }
    }

    public static String toCompressedString(final Serializable serializable) {
        if (null == serializable) {
            LoggerUtil.log(getLogger(), Level.WARNING, "Cannot serialize a null object!");
            return null;
        }

        try (final ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            toCompressedStream(baos, serializable);

            return baos.toString();
        } catch (final IOException ioException) {
            LoggerUtil.log(getLogger(), Level.SEVERE, ioException, "Trouble serializing object!");
        }

        return null;
    }

    public static String toString(final Serializable serializable) {
        if (null == serializable) {
            LoggerUtil.log(getLogger(), Level.WARNING, "Cannot serialize a null object!");
            return null;
        }

        try (final ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            toStream(baos, serializable);

            return baos.toString();
        } catch (final IOException ioException) {
            LoggerUtil.log(getLogger(), Level.SEVERE, ioException, "Trouble serializing object as a string!");
        }

        return null;
    }

    static <T extends Serializable> T fromStream(final InputStream is) {
        if (null == is) {
            LoggerUtil.log(getLogger(), Level.WARNING, "Cannot deserialize from a null input stream!");

            return null;
        }

        try (final ObjectInputStream ois = new ObjectInputStream(is)) {
            return (T) ois.readObject();
        } catch (final IOException | ClassNotFoundException exception) {
            LoggerUtil.log(getLogger(), Level.SEVERE, exception, "Trouble serializing object as a string!");
        }

        return null;
    }

    static <T extends Serializable> T fromCompressedString(final ByteArrayInputStream bais) {
        if (null == bais) {
            LoggerUtil.log(getLogger(), Level.WARNING, "Cannot deserialize from a null input stream!");

            return null;
        }

        try (final GZIPInputStream gis = new GZIPInputStream(bais)) {
            return fromStream(gis);
        } catch (final IOException ioException) {
            LoggerUtil.log(getLogger(), Level.SEVERE, ioException, "Trouble serializing object as a string!");
        }

        return null;
    }

    public static <T extends Serializable> T fromCompressedString(final String str) {
        if (StringUtils.isBlank(str)) {
            LoggerUtil.log(getLogger(), Level.WARNING, "Cannot deserialize from an empty string!");

            return null;
        }

        try (final ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes())) {
            return fromCompressedString(bais);
        } catch (final IOException ioException) {
            LoggerUtil.log(getLogger(), Level.SEVERE, ioException, "Trouble serializing object as a string!");
        }

        return null;
    }

    public static <T extends Serializable> T fromString(final String str) {
        if (StringUtils.isBlank(str)) {
            LoggerUtil.log(getLogger(), Level.WARNING, "Cannot deserialize from an empty string!");

            return null;
        }

        try (final ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes())) {
            return fromStream(bais);
        } catch (final IOException ioException) {
            LoggerUtil.log(getLogger(), Level.SEVERE, ioException, "Trouble serializing object as a string!");
        }

        return null;
    }

    /**
     * Default constructor not allowed
     */
    private StringUtil() {
    }
}
