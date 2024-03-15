package org.flossware.commons.soap;

import javax.xml.ws.Service;


/**
 * Record type to hold a SOAP service and the port class type.
 *
 * @author sfloess
 */
public record SoapRecord(Service service, Class portType) {
}
