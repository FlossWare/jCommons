package org.flossware.java.utils;

import jakarta.xml.ws.BindingProvider;

/**
 *
 * @author sfloess
 */
public final class CxfUrlUtils {
    private CxfUrlUtils() {
    }

    public static <T> T setUrl(final T port, final String url) {
        ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);

        return port;
    }

}
