package org.flossware.utils;

import jakarta.xml.ws.BindingProvider;
import org.apache.cxf.frontend.ClientProxy;

/**
 *
 * @author sfloess
 */
public final class SoapUtils {
    private SoapUtils() {
    }

    public static void setHeader(final Object service, final String name, final Object headerValue) {
        ClientProxy.getClient(service).getRequestContext().put(name, headerValue);
    }

    public static <T> T setUrl(final T port, final String url) {
        ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);

        return port;
    }
}
