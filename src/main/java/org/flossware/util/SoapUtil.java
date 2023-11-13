package org.flossware.util;

import jakarta.xml.ws.BindingProvider;
import org.apache.cxf.frontend.ClientProxy;

/**
 *
 * @author sfloess
 */
public final class SoapUtil {
    private SoapUtil() {
    }

    public static void setHeader(final Object service, final String name, final Object headerValue) {
        // To do:  var checks
        ClientProxy.getClient(service).getRequestContext().put(name, headerValue);
    }

    public static <T> T setUrl(final T port, final String url) {
                // To do:  var checks
        ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);

        return port;
    }
}
