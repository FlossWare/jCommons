package org.flossware.jcommons.util;

import jakarta.xml.ws.BindingProvider;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebServiceClient;
import java.util.Arrays;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.headers.Header;

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

    public static void setHeader(final Service service, final String name, final Object headerValue) {
        // To do:  var checks
        ClientProxy.getClient(service).getRequestContext().put(name, headerValue);
    }

    public static void setHeader(final Service service, final QName qname, final Object headerValue) {
        // To do:  var checks
        setHeader(service, Header.HEADER_LIST, new Header(qname, headerValue));
    }

    public static void setHeaders(final Service service, final Header... headers) {
            // To do:  var checks
        setHeader(service, Header.HEADER_LIST, Arrays.asList(headers));
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