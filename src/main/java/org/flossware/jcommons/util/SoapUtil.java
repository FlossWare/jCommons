package org.flossware.jcommons.util;

import jakarta.xml.ws.BindingProvider;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebServiceClient;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import org.apache.cxf.frontend.ClientProxy;

/**
 *
 * @author sfloess
 */
public final class SoapUtil {
    /**
     * Our logger.
     */
    private static final Logger logger = Logger.getLogger(SoapUtil.class.getName());

    /**
     * Return the logger.
     */
    private static Logger getLogger() {
        return logger;
    }

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

    public static QName computeQName(final WebServiceClient webServiceClient) {
                // To do:  var checks
        return new QName(webServiceClient.targetNamespace(), webServiceClient.name());
    }

    public static QName computeQName(final Class<? extends Service> klass) {
                // To do:  var checks
        return computeQName((WebServiceClient) klass.getAnnotation(WebServiceClient.class));
    }

    public static QName computeQName(final Service service) {
                // To do:  var checks
        return computeQName(service.getClass());
    }
}
