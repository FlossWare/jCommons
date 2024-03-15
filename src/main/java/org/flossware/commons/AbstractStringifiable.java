package org.flossware.commons;

import org.flossware.commons.util.StringUtil;

/**
 *
 * Default abstract base class of stringifiables.
 *
 * @author sfloess
 *
 */
public abstract class AbstractStringifiable extends AbstractBase implements Stringifiable {
    /**
     * Default constructor.
     */
    protected AbstractStringifiable() {
    }

    protected StringBuilder appendLine(final StringBuilder stringBuilder, final String... line) {
        return StringUtil.concat(stringBuilder, line).append(LINE_SEPARATOR_STRING);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StringBuilder toStringBuilder(final StringBuilder stringBuilder) {
        return toStringBuilder(stringBuilder, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StringBuilder toStringBuilder(final String prefix) {
        return toStringBuilder(new StringBuilder(), prefix);
    }

    /**
     * {@inheritDoc}
     *
     * @return the string representation of self.
     */
    @Override
    public String toString() {
        return toStringBuilder("").toString();
    }
}
